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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
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
    public int createPayment(CreationPaymentDto dto) {
        try {
            Payment payment = PaymentConverter.toEntity(dto);
            payment.setPaymentId(UUID.randomUUID().toString());
            return paymentMapper.insertPayment(payment);
        } catch (Exception e) {
            throw new RuntimeException(ErrorCode.PAYMENT_FAILED.getMessage(), e);
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

}