package com.stonehnh.admin.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OwnerDto {
    private String customerId;
    private BigDecimal percentageOwn;
}