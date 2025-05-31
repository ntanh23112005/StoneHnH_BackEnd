package com.stonehnh.review.converter;

import com.stonehnh.review.dto.request.CreationRateDetailDto;
import com.stonehnh.review.dto.response.RateResponseDto;
import com.stonehnh.review.entity.Rate;

import java.util.ArrayList;
import java.util.List;

public class RateConverter {
    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return List rate đã được convert từ entity -> dto
     */
    public static List<RateResponseDto> toDtoList(List<Rate> rates) {
        List<RateResponseDto> dtoList = new ArrayList<>();

        for (Rate rate : rates) {
            RateResponseDto dto = new RateResponseDto();
            dto.setRateId(rate.getRateId());
            dto.setHomestayId(rate.getHomestayId());
            dto.setCustomerId(rate.getCustomerId());
            dto.setComments(rate.getComments());
            dto.setRatedTime(rate.getRatedTime());
            dto.setPrice(rate.getPrice());
            dto.setLocation(rate.getLocation());
            dto.setCommunication(rate.getCommunication());
            dto.setExactly(rate.getExactly());
            dto.setCleanlinessLevel(rate.getCleanlinessLevel());
            dto.setAverageRate(rate.getAverageRate());
            dto.setRateTitle(rate.getRateTitle());

            dtoList.add(dto);
        }

        return dtoList;
    }
    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return 1 rate đã được convert từ entity -> dto
     */
    public static RateResponseDto toDto(Rate rate) {
        return RateResponseDto.builder()
                .rateId(rate.getRateId())
                .homestayId(rate.getHomestayId())
                .customerId(rate.getCustomerId())
                .comments(rate.getComments())
                .ratedTime(rate.getRatedTime())
                .price(rate.getPrice())
                .location(rate.getLocation())
                .communication(rate.getCommunication())
                .exactly(rate.getExactly())
                .cleanlinessLevel(rate.getCleanlinessLevel())
                .averageRate(rate.getAverageRate())
                .rateTitle(rate.getRateTitle())
                .build();
    }


    /**
     * Hàm convert data từ frontend xuống entity backend để làm việc với database
     *
     * @return 1 rate đã được convert từ dto -> entity
     */
    public static Rate toEntity(CreationRateDetailDto dto) {
        Rate rate = new Rate();
        rate.setRateId(dto.getRateId());
        rate.setHomestayId(dto.getHomestayId());
        rate.setCustomerId(dto.getCustomerId());
        rate.setComments(dto.getComments());
        rate.setRatedTime(dto.getRatedTime());
        rate.setPrice(dto.getPrice());
        rate.setLocation(dto.getLocation());
        rate.setCommunication(dto.getCommunication());
        rate.setExactly(dto.getExactly());
        rate.setCleanlinessLevel(dto.getCleanlinessLevel());
        rate.setAverageRate(dto.getAverageRate());
        rate.setRateTitle(dto.getRateTitle());
        return rate;
    }
}
