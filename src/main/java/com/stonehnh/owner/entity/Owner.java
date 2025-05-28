package com.stonehnh.owner.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    private String customerId;
    private String homestayId;
    private double percentageOwn;
}
