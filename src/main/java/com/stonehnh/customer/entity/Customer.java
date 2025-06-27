package com.stonehnh.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
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
