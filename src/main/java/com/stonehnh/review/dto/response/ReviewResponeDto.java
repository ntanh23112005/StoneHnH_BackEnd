package com.stonehnh.review.dto.response;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponeDto {
    private int reviewId;
    private String customerId;
    private String homestayId;
    private String reviewComments;
    private float rateCustomer;
    private Date createdTime;
}
