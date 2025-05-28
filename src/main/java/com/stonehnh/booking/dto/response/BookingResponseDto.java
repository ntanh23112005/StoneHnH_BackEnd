package com.stonehnh.booking.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponseDto {
    private String bookingId;
    private String customerId;
    private Double totalPrice;
    private Integer paymentStatus;
}
