package com.stonehnh.customer.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreationCustomerDto {
    private String customerId;
    private String customerName;
    private String email;
    private String phoneNumber;
    private String password;
    private String customerAddress;
    private Date createdDate;
    private String customerPicture;
    private Boolean verifyStatus;
    private Boolean accountStatus;
}
