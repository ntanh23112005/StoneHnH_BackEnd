package com.stonehnh.admin.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class HomestayDto {
    private String homestayId;
    private String homestayName;
    private String countryAddress;
    private String areaAddress;
    private String detailAddress;
    private BigDecimal dailyPrice;
    private BigDecimal hourlyPrice;
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

    private List<HomestayImageDto> images;
    private List<HomestayRuleDto> rules;
    private List<OwnerDto> owners;
    private List<ReviewDto> reviews;
    private List<RateDto> rates;
}
