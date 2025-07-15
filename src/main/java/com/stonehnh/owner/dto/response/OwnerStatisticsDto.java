package com.stonehnh.owner.dto.response;

import lombok.Data;

@Data
public class OwnerStatisticsDto {
    private Double totalRevenue;
    private Integer activeHomestays;
    private Integer inactiveHomestays;
    private Integer bookingStatus0;
    private Integer bookingStatus1;
    private Integer bookingStatus2;
}
