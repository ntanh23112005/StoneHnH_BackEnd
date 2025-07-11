package com.stonehnh.admin.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReviewDto {
    private String customerId;
    private String reviewComments;
    private BigDecimal rateCustomer;
    private LocalDateTime createdTime;
}
