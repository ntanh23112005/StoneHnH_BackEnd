package com.stonehnh.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CreationBookingDetailDto {
    private String id;
    private String bookingId;
    private String homestayId;
    private Date bookingTime;
    private Date checkInTime;
    private Date checkOutTime;
    private int numberOfCustomers;
    private int numberOfPets;
}
