package com.stonehnh.converter;

import com.stonehnh.dto.request.CreationPaymentDto;
import com.stonehnh.dto.response.PaymentResponseDto;
import com.stonehnh.entity.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentConverter {
    /**
     * Convert entity -> DTO
     */
    public static PaymentResponseDto toDto(Payment entity) {
        return PaymentResponseDto.builder()
                .paymentId(entity.getPaymentId())
                .bookingId(entity.getBookingId())
                .paymentName(entity.getPaymentName())
                .createdTime(entity.getCreatedTime())
                .status(entity.isStatus())
                .build();
    }

    /**
     * Convert list entity -> list DTO
     */
    public static List<PaymentResponseDto> toDtoList(List<Payment> entities) {
        List<PaymentResponseDto> dtoList = new ArrayList<>();
        for (Payment entity : entities) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

    /**
     * Convert DTO -> entity
     */
    public static Payment toEntity(CreationPaymentDto dto) {
        Payment entity = new Payment();
        entity.setPaymentId(dto.getPaymentId());
        entity.setBookingId(dto.getBookingId());
        entity.setPaymentName(dto.getPaymentName());
        entity.setCreatedTime(dto.getCreatedTime());
        entity.setStatus(dto.isStatus());
        return entity;
    }

    /**
     * Convert list DTO -> list entity
     */
    public static List<Payment> toEntityList(List<CreationPaymentDto> dtoList) {
        List<Payment> entityList = new ArrayList<>();
        for (CreationPaymentDto dto : dtoList) {
            entityList.add(toEntity(dto));
        }
        return entityList;
    }
}
