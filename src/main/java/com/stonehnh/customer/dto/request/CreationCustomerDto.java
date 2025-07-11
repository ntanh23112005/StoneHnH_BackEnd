package com.stonehnh.customer.dto.request;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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
