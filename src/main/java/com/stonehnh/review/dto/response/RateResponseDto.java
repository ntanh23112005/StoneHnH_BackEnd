package com.stonehnh.review.dto.response;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RateResponseDto {
    private String rateId;
    private String homestayId;
    private String customerId;
    private String comments;
    private Date ratedTime;
    private float price;
    private int location;
    private float communication;
    private float exactly;
    private float cleanlinessLevel;
    private float averageRate;
    private String rateTitle;
}
