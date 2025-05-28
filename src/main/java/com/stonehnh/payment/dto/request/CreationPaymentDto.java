package com.stonehnh.payment.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class CreationPaymentDto {
    private String paymentId;
    private String bookingId;
    private String paymentName;
    private Date createdTime;
    private boolean status;
}
