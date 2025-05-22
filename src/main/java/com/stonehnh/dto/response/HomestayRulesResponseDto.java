package com.stonehnh.dto.response;

import lombok.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomestayRulesResponseDto {
    private int id;
    private String homestayId;
    private String ruleText;
    private String policyText;
    private Date createdAt;
}
