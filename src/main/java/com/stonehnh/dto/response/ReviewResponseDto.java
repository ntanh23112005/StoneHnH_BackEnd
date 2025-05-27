package com.stonehnh.dto.response;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDto {
    private int reviewId;
    private String customerId;
    private String homestayId;
    private String reviewComments;
    private float rateCustomer;
    private Date createdTime;
}
