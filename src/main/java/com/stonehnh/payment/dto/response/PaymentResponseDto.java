package com.stonehnh.payment.dto.response;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDto {
    private String paymentId;
    private String bookingId;
    private String paymentName;
    private LocalDateTime createdTime;
    private boolean status;
}
