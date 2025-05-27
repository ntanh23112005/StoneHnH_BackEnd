package com.stonehnh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private String bookingId;
    private String customerId;
    private Double totalPrice;
    private Integer paymentStatus;
}
