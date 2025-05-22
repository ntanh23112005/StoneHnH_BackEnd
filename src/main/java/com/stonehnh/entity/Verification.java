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
public class Verification {
    private int id;
    private String customerId;
    private String authenicationCode;
    private Date createdTime;
    private Boolean isVerified;
}