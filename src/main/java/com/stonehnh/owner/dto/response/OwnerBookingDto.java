package com.stonehnh.owner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerBookingDto {
    private String bookingId;
    private String customerId;
    private Double totalPrice;
    private Integer paymentStatus;
    private String customerName;
}
