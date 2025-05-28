package com.stonehnh.payment.service.impl;

import com.stonehnh.payment.converter.PaymentConverter;
import com.stonehnh.payment.dto.request.CreationPaymentDto; // Giả sử có
import com.stonehnh.payment.dto.response.PaymentResponseDto; // Giả sử có
import com.stonehnh.payment.entity.Payment;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.payment.mapper.PaymentMapper;
import com.stonehnh.payment.service.PaymentService;
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
            throw new AppException(ErrorCode.PAYMENT_NOT_FOUND);
        }
        paymentDto.setPaymentId(paymentId);
        return paymentMapper.updatePayment(PaymentConverter.toEntity(paymentDto));
    }

    @Override
    public int deletePayment(String paymentId) {
        boolean exists = paymentMapper.isExistedPaymentById(paymentId);
        if (!exists) {
            throw new AppException(ErrorCode.PAYMENT_NOT_FOUND);
        }
        return paymentMapper.deletePaymentById(paymentId);
    }

    @Override
    public PaymentResponseDto findPaymentById(String paymentId) {
        Payment payment = paymentMapper.findPaymentById(paymentId);
        if (payment == null) {
            throw new AppException(ErrorCode.PAYMENT_NOT_FOUND);
        }
        return PaymentConverter.toDto(payment);
    }
}