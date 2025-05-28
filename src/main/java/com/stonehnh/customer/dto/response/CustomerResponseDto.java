package com.stonehnh.customer.dto.response;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
