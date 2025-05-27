package com.stonehnh.service.impl;

import com.stonehnh.converter.VerificationConverter;
import com.stonehnh.dto.response.VerificationResponseDto;
import com.stonehnh.entity.Verification;
import com.stonehnh.mapper.VerificationMapper;
import com.stonehnh.service.VerificationService;

import java.util.List;

public class VerificationImpl implements VerificationService {
    private final VerificationMapper verificationMapper;

    public VerificationImpl(VerificationMapper verificationMapper) {
        this.verificationMapper = verificationMapper;
    }

    @Override
    public List<VerificationResponseDto> getAllReview() {
        List<Verification> verificationList = verificationMapper.findAllVerifications();

        List<VerificationResponseDto>responseDtos = VerificationConverter.toDtoList(verificationList);
        return responseDtos;
    }
}
