package com.stonehnh.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnerResponseDto {
    private String customerId;
    private String homestayId;
    private double percentageOwn;
}
