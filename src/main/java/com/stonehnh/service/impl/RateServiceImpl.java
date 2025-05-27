package com.stonehnh.service.impl;

import com.stonehnh.converter.RateConverter;
import com.stonehnh.dto.response.RateResponseDto;

import com.stonehnh.entity.Rate;
import com.stonehnh.mapper.RateMapper;
import com.stonehnh.service.RateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    private  final RateMapper rateMapper;

    public RateServiceImpl(RateMapper rateMapper) {
        this.rateMapper = rateMapper;
    }

    @Override
    public List<RateResponseDto> getAllRate() {
        List<Rate> rateList = rateMapper.findAllRates();

        List<RateResponseDto> responseDtos = RateConverter.toDtoList(rateList);
        return responseDtos;
    }
}
