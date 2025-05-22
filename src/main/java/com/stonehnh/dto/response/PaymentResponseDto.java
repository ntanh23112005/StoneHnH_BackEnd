package com.stonehnh.dto.response;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDto {
    private String paymentId;
    private String bookingId;
    private String paymentName;
    private Date createdTime;
    private boolean status;
}
