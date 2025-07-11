package com.stonehnh.admin.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RateDto {
    private String customerId;
    private String comments;
    private BigDecimal averageRate;
    private String rateTitle;
}
