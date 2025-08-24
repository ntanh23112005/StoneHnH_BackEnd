package com.stonehnh.booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerBookingResponseDto {
    private String bookingId;
    private String customerId;
    private Double totalPrice;
}
