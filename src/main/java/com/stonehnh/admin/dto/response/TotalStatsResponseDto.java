package com.stonehnh.admin.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TotalStatsResponseDto {
    private double totalPriceBooking;
    private int totalBookings;
    private int totalUsers;
    private int totalHomestays;
}
