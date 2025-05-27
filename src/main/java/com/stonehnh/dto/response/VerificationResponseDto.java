package com.stonehnh.dto.response;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationResponseDto {
    private int id;
    private String customerId;
    private String authenicationCode;
    private Date createdTime;
    private Boolean isVerified;
}
