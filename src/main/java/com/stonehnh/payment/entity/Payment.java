package com.stonehnh.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private String paymentId;
    private String bookingId;
    private String paymentName;
    private Date createdTime;
    private boolean status;
}
