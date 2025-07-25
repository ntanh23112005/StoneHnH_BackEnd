package com.stonehnh.payment.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreationPaymentDto {
    private String paymentId;
    private String bookingId;
    private String paymentName;
    private LocalDateTime createdTime;
    private boolean status;
}
