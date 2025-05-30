package com.stonehnh.homestay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomestayRule {
    private int id;
    private String homestayId;
    private String ruleText;
    private String policyText;
    private Date createdAt;
}
