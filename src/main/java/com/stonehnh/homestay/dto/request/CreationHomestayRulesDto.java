package com.stonehnh.homestay.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CreationHomestayRulesDto {
    private int id;
    private String homestayId;
    private String ruleText;
    private String policyText;
    private Date createdAt;
}
