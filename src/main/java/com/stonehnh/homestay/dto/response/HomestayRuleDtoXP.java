package com.stonehnh.homestay.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomestayRuleDtoXP {
    private int id;
    private String homestayId;
    private String ruleText;
    private String policyText;
}