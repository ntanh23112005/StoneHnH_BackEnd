package com.stonehnh.review.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CreationReviewDetailDto {
    private int reviewId;
    private String customerId;
    private String homestayId;
    private String reviewComments;
    private float rateCustomer;
    private Date createdTime;
}
