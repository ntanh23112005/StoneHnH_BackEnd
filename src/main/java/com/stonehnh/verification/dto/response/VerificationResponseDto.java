package com.stonehnh.verification.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerificationResponseDto {
    private int id;
    private String customerId;
    private String authenicationCode;
    private Date createdTime;
    private Boolean isVerified;
}
