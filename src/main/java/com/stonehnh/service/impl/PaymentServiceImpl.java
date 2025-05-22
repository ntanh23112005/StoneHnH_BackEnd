package com.stonehnh.service.impl;

import com.stonehnh.converter.PaymentConverter;
import com.stonehnh.dto.request.CreationPaymentDto; // Giả sử có
import com.stonehnh.dto.response.PaymentResponseDto; // Giả sử có
import com.stonehnh.entity.Payment;
import com.stonehnh.enums.ErrorCode;
import com.stonehnh.exception.AppException;
import com.stonehnh.mapper.PaymentMapper;
import com.stonehnh.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    @Override
    public List<PaymentResponseDto> getAllPayments() {
        List<Payment> payments = paymentMapper.findAllPayments();
        return PaymentConverter.toDtoList(payments);
    }

    @Override
    public int createPayment(CreationPaymentDto creationPaymentDto) {
        Payment payment = PaymentConverter.toEntity(creationPaymentDto);
        payment.setPaymentId(UUID.randomUUID().toString());
        return paymentMapper.insertPayment(payment);
    }

    @Override
    public int updatePayment(String paymentId, CreationPaymentDto paymentDto) {
        boolean exists = paymentMapper.isExistedPaymentById(paymentId);
        if (!exists) {
            throw new AppException(ErrorCode.PAYMENT_NOT_EXISTED);
        }
        paymentDto.setPaymentId(paymentId);
        return paymentMapper.updatePayment(PaymentConverter.toEntity(paymentDto));
    }

    @Override
    public int deletePayment(String paymentId) {
        boolean exists = paymentMapper.isExistedPaymentById(paymentId);
        if (!exists) {
            throw new AppException(ErrorCode.PAYMENT_NOT_EXISTED);
        }
        return paymentMapper.deletePaymentById(paymentId);
    }

    @Override
    public PaymentResponseDto findPaymentById(String paymentId) {
        Payment payment = paymentMapper.findPaymentById(paymentId);
        if (payment == null) {
            throw new AppException(ErrorCode.PAYMENT_NOT_EXISTED);
        }
        return PaymentConverter.toDto(payment);
    }
}