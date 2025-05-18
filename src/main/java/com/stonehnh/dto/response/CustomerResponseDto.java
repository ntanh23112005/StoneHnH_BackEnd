package com.stonehnh.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CustomerResponseDto {
    private String customerId;
    private String customerName;
    private String email;
    private String phoneNumber;
    private String customerAddress;
    private Date createdDate;
    private String customerPicture;
    private Boolean verifyStatus;
    private Boolean accountStatus;
}
