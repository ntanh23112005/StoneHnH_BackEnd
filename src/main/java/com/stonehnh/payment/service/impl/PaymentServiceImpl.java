package com.stonehnh.payment.service.impl;

import com.stonehnh.booking.mapper.BookingMapper;
import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.payment.converter.PaymentConverter;
import com.stonehnh.payment.dto.request.CreationPaymentDto; // Giả sử có
import com.stonehnh.payment.dto.response.PaymentResponseDto; // Giả sử có
import com.stonehnh.payment.dto.response.PaymentWithDetailDto;
import com.stonehnh.payment.entity.Payment;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.payment.mapper.PaymentMapper;
import com.stonehnh.payment.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    private final BookingMapper bookingMapper;

    public PaymentServiceImpl(PaymentMapper paymentMapper, BookingMapper bookingMapper) {
        this.paymentMapper = paymentMapper;
        this.bookingMapper = bookingMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponseDto> getAllPayments() {
        try {
            return PaymentConverter.toDtoList(paymentMapper.findAllPayments());
        } catch (Exception e) {
            throw new RuntimeException(ErrorCode.PAYMENT_FAILED.getMessage(), e);
        }
    }


    @Override
    @Transactional
    public ApiResponse<?> createPayment(CreationPaymentDto dto) {
        try {
            Payment payment = PaymentConverter.toEntity(dto);
            payment.setPaymentId(UUID.randomUUID().toString());

            int result = paymentMapper.insertPayment(payment);

            if (result > 0) {
                int updateBooking = bookingMapper.updatePaymentStatus(dto.getBookingId(), 1); // 1 = đã thanh toán

                if (updateBooking <= 0) {
                    throw new AppException();
                }

                return ApiResponse.builder()
                        .success(true)
                        .message("Tạo thanh toán mới thành công.")
                        .data(result) // hoặc data = paymentId nếu bạn cần
                        .build();
            } else {
                return ApiResponse.builder()
                        .success(false)
                        .message("Tạo thanh toán thất bại.")
                        .data(null)
                        .build();
            }
        } catch (Exception e) {
            throw new AppException();
        }
    }

    @Override
    @Transactional
    public int updatePayment(String paymentId, CreationPaymentDto dto) {
        if (!paymentMapper.isExistedPaymentById(paymentId)) {
            throw new AppException(ErrorCode.PAYMENT_NOT_FOUND);
        }
        dto.setPaymentId(paymentId);
        return paymentMapper.updatePayment(PaymentConverter.toEntity(dto));
    }


    @Override
    @Transactional
    public int deletePayment(String paymentId) {
        if (!paymentMapper.isExistedPaymentById(paymentId)) {
            throw new AppException(ErrorCode.PAYMENT_NOT_FOUND);
        }
        return paymentMapper.deletePaymentById(paymentId);
    }


    @Override
    public PaymentResponseDto findPaymentById(String paymentId) {
        return Optional.ofNullable(paymentMapper.findPaymentById(paymentId))
                .map(PaymentConverter::toDto)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentWithDetailDto> findPaymentsByCustomerId(String customerId) {
        return paymentMapper.findPaymentsByCustomerId(customerId);
    }


}