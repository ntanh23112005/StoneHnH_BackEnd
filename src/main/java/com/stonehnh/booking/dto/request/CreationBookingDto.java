package com.stonehnh.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreationBookingDto {
    private String bookingId;
    private String customerId;
    private Double totalPrice;
    private Integer paymentStatus;
}
