package com.stonehnh.homestay.service.impl;

import com.stonehnh.homestay.converter.HomestayConverter;
import com.stonehnh.homestay.dto.request.CreationHomestayDto;
import com.stonehnh.homestay.dto.response.HomestayHomePageResponseDto;
import com.stonehnh.homestay.dto.response.HomestayResponseDto;
import com.stonehnh.homestay.entity.Homestay;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.homestay.service.HomestayService;
import com.stonehnh.homestay.mapper.HomestayMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HomestayServiceImpl implements HomestayService {

    private final HomestayMapper homestayMapper;

    public HomestayServiceImpl(HomestayMapper homestayMapper) {
        this.homestayMapper = homestayMapper;
    }

    @Override
    public List<HomestayResponseDto> getAllHomestays() {
        List<Homestay> homestays = homestayMapper.findAllHomestays();
        return HomestayConverter.toDtoList(homestays);
    }

    @Override
    public HomestayResponseDto findHomestayById(String homestayId) {
        Homestay homestay = homestayMapper.findHomestayById(homestayId);
        if (homestay == null) {
            throw new AppException(ErrorCode.HOMESTAY_NOT_FOUND);
        }
        return HomestayConverter.toDto(homestay);
    }

    @Override
    public List<HomestayHomePageResponseDto> getHomestayHomePage() {
        return homestayMapper.selectHomestayHomePage();
    }

    @Override
    public int createHomestay(CreationHomestayDto homestayDto) {
        Homestay homestay = HomestayConverter.toEntity(homestayDto);
        homestay.setHomestayId(UUID.randomUUID().toString());
        return homestayMapper.insertHomestay(homestay);
    }

    @Override
    public int updateHomestay(String homestayId, CreationHomestayDto homestayDto) {
        boolean exists = homestayMapper.isExistedHomestayById(homestayId);
        if (!exists) {
            throw new AppException(ErrorCode.HOMESTAY_NOT_FOUND);
        }

        Homestay homestay = HomestayConverter.toEntity(homestayDto);
        homestay.setHomestayId(homestayId);
        return homestayMapper.updateHomestay(homestay);
    }

    @Override
    public int deleteHomestay(String homestayId) {
        boolean exists = homestayMapper.isExistedHomestayById(homestayId);
        if (!exists) {
            throw new AppException(ErrorCode.HOMESTAY_NOT_FOUND);
        }

        return homestayMapper.deleteHomestayById(homestayId);
    }
}