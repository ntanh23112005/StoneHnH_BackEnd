package com.stonehnh.booking.dto.request;

import lombok.Data;

@Data
public class CreationBookingWrapperDto {
    private CreationBookingDto booking;
    private CreationBookingDetailDto bookingDetail;
}
