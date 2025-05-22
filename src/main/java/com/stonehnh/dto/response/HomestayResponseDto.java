package com.stonehnh.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomestayResponseDto {
    private String homestayId;
    private String homestayName;
    private String countryAddress;
    private String areaAddress;
    private String detailAddress;
    private BigDecimal dailyPrice;
    private BigDecimal hourlyPrice;
    private String type;
    private boolean status;

    // For filter & detail pages
    private String typeStyle;
    private boolean havePet;
    private int maxCustomer;
    private int numberOfBeds;
    private int numberOfBathrooms;
    private String conveniences;
    private String options;
    private boolean entranceParking;
    private String bedroomDetails;
    private String bathroomDetails;
    private String supportEquipments;
}
