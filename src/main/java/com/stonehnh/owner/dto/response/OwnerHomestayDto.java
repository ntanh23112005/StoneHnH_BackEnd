package com.stonehnh.owner.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class OwnerHomestayDto {
    private String homestayId;
    private String homestayName;
    private String countryAddress;
    private String areaAddress;
    private String detailAddress;
    private Double dailyPrice;
    private Double hourlyPrice;
    private String type;
    private Boolean status;
    private String typeStyle;
    private Boolean havePet;
    private Integer maxCustomer;
    private Integer numberOfBeds;
    private Integer numberOfBathrooms;
    private String conveniences;
    private String options;
    private Boolean entranceParking;
    private String bedroomDetails;
    private String bathroomDetails;
    private String supportEquipments;
    private List<String> images;
}