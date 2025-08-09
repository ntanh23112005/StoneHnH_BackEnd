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
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RateServiceImpl implements RateService {
    private final RateMapper rateMapper;

    public RateServiceImpl(RateMapper rateMapper) {
        this.rateMapper = rateMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RateResponseDto> getAllRates() {
        try {
            return RateConverter.toDtoList(rateMapper.findAllRates());
        } catch (Exception e) {
            throw new RuntimeException(ErrorCode.RATE_NOT_FOUND.getMessage(), e);
        }
    }


    @Override
    @Transactional
    public int createNewRate(CreationRateDetailDto dto) {
        try {
            Rate rate = RateConverter.toEntity(dto);
            rate.setRateId(UUID.randomUUID().toString());
            return rateMapper.insertRate(rate);
        } catch (Exception e) {
            throw new AppException(ErrorCode.RATE_CREATE_FAILED);
        }
    }


    @Override
    @Transactional
    public int updateRate(String rateId, Rate rate) {
        if (!rateMapper.isExistedRateById(rateId)) {
            throw new AppException(ErrorCode.RATE_NOT_FOUND);
        }
        return rateMapper.updateRate(rate);
    }


    @Override
    @Transactional
    public int deleteRateById(String rateId) {
        if (!rateMapper.isExistedRateById(rateId)) {
            throw new AppException(ErrorCode.RATE_NOT_FOUND);
        }
        return rateMapper.deleteRate(rateId);
    }


    @Override
    public RateResponseDto findRateById(String rateId) {
        return Optional.ofNullable(rateMapper.findRateById(rateId))
                .map(RateConverter::toDto)
                .orElseThrow(() -> new AppException(ErrorCode.RATE_NOT_FOUND));
    }


    @Override
    public List<RateResponseDto> findRateByHomestayId(String homestayId) {
        List<Rate> list = rateMapper.findRateByHomestayId(homestayId);
        if (list == null || list.isEmpty()) {
            System.out.println(ErrorCode.RATE_NOT_FOUND);
            return Collections.emptyList();
        }
        return RateConverter.toDtoList(list);
    }


}
