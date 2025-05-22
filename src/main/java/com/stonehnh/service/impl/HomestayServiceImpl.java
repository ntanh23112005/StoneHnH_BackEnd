package com.stonehnh.service.impl;

import com.stonehnh.converter.HomestayConverter;
import com.stonehnh.dto.request.CreationHomestayDto;
import com.stonehnh.dto.response.HomestayResponseDto;
import com.stonehnh.entity.Homestay;
import com.stonehnh.enums.ErrorCode;
import com.stonehnh.exception.AppException;
import com.stonehnh.mapper.HomestayMapper;
import com.stonehnh.service.HomestayService;
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