package com.stonehnh.review.service.impl;

import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.review.converter.RateConverter;
import com.stonehnh.review.dto.request.CreationRateDetailDto;
import com.stonehnh.review.dto.response.RateResponseDto;
import com.stonehnh.review.entity.Rate;
import com.stonehnh.review.mapper.RateMapper;
import com.stonehnh.review.service.RateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RateServiceImpl implements RateService {
    private final RateMapper rateMapper;

    public RateServiceImpl(RateMapper rateMapper) {
        this.rateMapper = rateMapper;
    }

    @Override
    public List<RateResponseDto> getAllRates() {
        List<Rate> rateList = rateMapper.findAllRates();
        return RateConverter.toDtoList(rateList);
    }

    @Override
    public int createNewRate(CreationRateDetailDto creationRateDetailDto) {
        Rate rate = RateConverter.toEntity(creationRateDetailDto);
        rate.setRateId(String.valueOf(UUID.randomUUID()));
        return rateMapper.insertRate(rate);
    }

    @Override
    public int updateRate(String rateId, Rate rate) {
        boolean isExistedRate = rateMapper.isExistedRateById(rateId);
        if (!isExistedRate) {
            throw new AppException(ErrorCode.RATE_NOT_FOUND);
        }
        return rateMapper.updateRate(rate);
    }

    @Override
    public int deleteRateById(String rateId) {
        boolean isExistedRate = rateMapper.isExistedRateById(rateId);
        if (!isExistedRate) {
            throw new AppException(ErrorCode.RATE_NOT_FOUND);
        }
        return rateMapper.deleteRate(rateId);
    }

    @Override
    public RateResponseDto findRateById(String rateId) {
        Rate rate = rateMapper.findRateById(rateId);
        if (rate == null) {
            throw new AppException(ErrorCode.RATE_NOT_FOUND);
        }
        return RateConverter.toDto(rate);
    }

    @Override
    public List<RateResponseDto> findRateByHomestayId(String homestayId) {
        List<Rate> rateList = rateMapper.findRateByHomestayId(homestayId);
        if (rateList == null) {
            throw new AppException(ErrorCode.RATE_NOT_FOUND);
        }
        return RateConverter.toDtoList(rateList);
    }
}
