package com.stonehnh.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreationBookingDto {
    private String bookingId;
    private String customerId;
    private Double totalPrice;

    //0: Chưa thanh toán
    //1: Hoàn tất
    //2: Đã hủy
    private Integer paymentStatus;
}
