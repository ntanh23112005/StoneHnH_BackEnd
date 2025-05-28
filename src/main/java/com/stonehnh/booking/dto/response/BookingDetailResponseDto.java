package com.stonehnh.booking.dto.response;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDetailResponseDto {
    private String id;
    private String bookingId;
    private String homestayId;
    private Date bookingTime;
    private Date checkInTime;
    private Date checkOutTime;
    private int numberOfCustomers;
    private int numberOfPets;
}
