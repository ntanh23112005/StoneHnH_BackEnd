package com.stonehnh.converter;

import com.stonehnh.dto.request.CreationRateDto;
import com.stonehnh.dto.response.RateResponseDto;
import com.stonehnh.entity.Rate;

import java.util.ArrayList;
import java.util.List;

public class RateConverter {
    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return List Rate đã được convert từ entity -> dto
     */
    public static List<RateResponseDto> toDtoList(List<Rate> rates) {
        List<RateResponseDto> dtoList = new ArrayList<>();

        for (Rate r : rates) {
            RateResponseDto dto = new RateResponseDto();
            dto.setRateId(r.getRateId());
            dto.setHomestayId(r.getHomestayId());
            dto.setCustomerId(r.getCustomerId());
            dto.setComments(r.getComments());
            dto.setRatedTime(r.getRatedTime());
            dto.setPrice(r.getPrice());
            dto.setLocation(r.getLocation());
            dto.setCommunication(r.getCommunication());
            dto.setExactly(r.getExactly());
            dto.setCleanlinessLevel(r.getCleanlinessLevel());
            dto.setAverageRate(r.getAverageRate());
            dto.setRateTitle(r.getRateTitle());

            dtoList.add(dto);
        }
        return dtoList;
    }
    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return 1 rate đã được convert từ entity -> dto
     */
    public static RateResponseDto toDto(Rate r) {
        return RateResponseDto.builder()
                .rateId(r.getRateId())
                .homestayId(r.getHomestayId())
                .customerId(r.getCustomerId())
                .comments(r.getComments())
                .ratedTime(r.getRatedTime())
                .price(r.getPrice())
                .location(r.getLocation())
                .communication(r.getCommunication())
                .exactly(r.getExactly())
                .cleanlinessLevel(r.getCleanlinessLevel())
                .averageRate(r.getAverageRate())
                .rateTitle(r.getRateTitle())
                .build();
    }

    /**
     * Hàm convert data từ frontend xuống entity backend để làm việc với database
     *
     * @return List rate đã được convert từ dto -> entity
     */
    public static Rate toEntity(CreationRateDto dto) {
        Rate r = new Rate();
        r.setRateId(dto.getRateId());
        r.setHomestayId(dto.getHomestayId());
        r.setCustomerId(dto.getCustomerId());
        r.setComments(dto.getComments());
        r.setRatedTime(dto.getRatedTime());
        r.setPrice(dto.getPrice());
        r.setLocation(dto.getLocation());
        r.setCommunication(dto.getCommunication());
        r.setExactly(dto.getExactly());
        r.setCleanlinessLevel(dto.getCleanlinessLevel());
        r.setAverageRate(dto.getAverageRate());
        r.setRateTitle(dto.getRateTitle());

        return r;
    }
    }