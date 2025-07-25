package com.stonehnh.payment.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentWithDetailDto {
    private String bookingId;
    private LocalDateTime createdTime;
    private Integer status;
    private double totalPrice;

    private String homestayId;
    private String homestayName;
    private LocalDateTime bookingTime;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private Integer numberOfCustomers;
    private Integer numberOfPets;
}