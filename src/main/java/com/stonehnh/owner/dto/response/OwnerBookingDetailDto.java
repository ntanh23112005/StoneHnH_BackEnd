package com.stonehnh.owner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerBookingDetailDto {
    private String bookingId;
    private String customerId;
    private Double totalPrice;
    private Integer paymentStatus;

    private LocalDateTime bookingTime;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private Integer numberOfCustomers;
    private Integer numberOfPets;

    private String homestayId;
    private String homestayName;
}
