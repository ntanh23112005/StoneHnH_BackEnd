package com.stonehnh.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {
    private int reviewId;
    private String customerId;
    private String homestayId;
    private String reviewComments;
    private float rateCustomer;
    private Date createdTime;
}
