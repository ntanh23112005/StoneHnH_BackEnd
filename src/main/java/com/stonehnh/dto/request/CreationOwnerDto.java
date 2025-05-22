package com.stonehnh.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreationOwnerDto {
    private String customerId;
    private String homestayId;
    private double percentageOwn;
}
