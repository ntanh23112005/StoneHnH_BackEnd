package com.stonehnh.review.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CreationRateDetailDto {
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
