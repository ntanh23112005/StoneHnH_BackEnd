package com.stonehnh.booking.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class BookingWithDetailDto {
    private String bookingId;
    private String customerId;
    private String customerName;
    private String homestayId;
    private String homestayName;
    private BigDecimal totalPrice;
    private Integer paymentStatus;
    private Timestamp bookingDate;
}