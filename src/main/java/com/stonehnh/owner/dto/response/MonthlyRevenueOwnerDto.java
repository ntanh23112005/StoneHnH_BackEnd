package com.stonehnh.owner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyRevenueOwnerDto {
    private Integer month;
    private Double revenue;
}
