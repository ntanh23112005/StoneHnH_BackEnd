package com.stonehnh.homestay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Homestay {
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
