package com.stonehnh.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CreationReviewDto {
    private int reviewId;
    private String customerId;
    private String homestayId;
    private String reviewComments;
    private float rateCustomer;
    private Date createdTime;
}
