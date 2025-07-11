package com.stonehnh.admin.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HomestayRuleDto {
    private String ruleText;
    private String policyText;
    private LocalDateTime createdAt;
}
