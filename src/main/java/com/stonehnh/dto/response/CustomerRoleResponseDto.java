package com.stonehnh.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRoleResponseDto {
    private String customerId;
    private String roleId;
}
