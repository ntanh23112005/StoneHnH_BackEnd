package com.stonehnh.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetail {

    private String id;
    private String bookingId;
    private String homestayId;
    private Date bookingTime;
    private Date checkInTime;
    private Date checkOutTime;
    private int numberOfCustomers;
    private int numberOfPets;
}
