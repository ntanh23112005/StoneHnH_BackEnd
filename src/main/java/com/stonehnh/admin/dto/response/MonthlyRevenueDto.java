package com.stonehnh.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyRevenueDto {
    private int month;
    private double revenue;
}
