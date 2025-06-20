package com.stonehnh.homestay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomestayHomePageResponseDto {
    private String homestayId;
    private String homestayName;
    private String areaAddress;
    private BigDecimal dailyPrice;
    private BigDecimal hourlyPrice;
    private String type;
    private boolean status;

    private String typeStyle;
    private int maxCustomer;
    private int numberOfBeds;
    private int numberOfBathrooms;
    private String supportEquipments;

    // rates
    private Float averageRate;

    //home_img
    private String imageList; // VD: "hinh1.jpg,hinh2.jpg,hinh3.jpg"
}
