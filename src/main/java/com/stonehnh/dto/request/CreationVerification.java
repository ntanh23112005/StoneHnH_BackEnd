package com.stonehnh.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CreationVerification {
    private int id;
    private String customerId;
    private String authenicationCode;
    private Date createdTime;
    private Boolean isVerified;
}
