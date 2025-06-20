package com.stonehnh.homestay.dto.response;

import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.owner.dto.response.OwnerResponseDto;
import com.stonehnh.review.dto.response.RateResponseDto;
import com.stonehnh.review.dto.response.ReviewResponeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomestayDetailResponseDto {
    private HomestayResponseDto homestay;
    private HomestayRulesResponseDto homestayRule;
    private List<HomestayImagesResponseDto> images;
    private OwnerResponseDto owner;
    private CustomerResponseDto customer;
    private List<ReviewResponeDto> reviews;
    private List<RateResponseDto> rates;
}
