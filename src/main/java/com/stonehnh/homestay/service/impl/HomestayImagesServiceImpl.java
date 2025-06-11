package com.stonehnh.homestay.service.impl;

import com.stonehnh.homestay.converter.HomestayImagesConverter;
import com.stonehnh.homestay.dto.request.CreationHomestayImagesDto;
import com.stonehnh.homestay.dto.response.HomestayImagesResponseDto;
import com.stonehnh.homestay.entity.HomestayImages;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.homestay.service.HomestayImageService;
import com.stonehnh.homestay.mapper.HomestayImagesMapper;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomestayImagesServiceImpl implements HomestayImageService {

    private final HomestayImagesMapper homestayImagesMapper;

    public HomestayImagesServiceImpl(HomestayImagesMapper homestayImagesMapper) {
        this.homestayImagesMapper = homestayImagesMapper;
    }

    @Override
    public List<HomestayImagesResponseDto> getImagesByHomestayId(String homestayId) {
        List<HomestayImages> images = homestayImagesMapper.findImagesByHomestayId(homestayId);
        return HomestayImagesConverter.toDtoList(images);
    }

    @Override
    public int createImage(CreationHomestayImagesDto dto) {
        HomestayImages entity = HomestayImagesConverter.toEntity(dto);
        return homestayImagesMapper.insertImage(entity);
    }

    @Override
    public int updateImage(int id, CreationHomestayImagesDto dto) {
        boolean exists = homestayImagesMapper.existsImageById(id);
        if (!exists) {
            throw new AppException(ErrorCode.HOMESTAY_IMAGE_NOT_FOUND);
        }

        HomestayImages entity = HomestayImagesConverter.toEntity(dto);
        entity.setId(id);
        return homestayImagesMapper.updateImage(entity);
    }

    @Override
    public int deleteImage(int id) {
        boolean exists = homestayImagesMapper.existsImageById(id);
        if (!exists) {
            throw new AppException(ErrorCode.HOMESTAY_IMAGE_NOT_FOUND);
        }

        return homestayImagesMapper.deleteImageById(id);
    }

    @Override
    public HomestayImagesResponseDto findImageById(int id) {
        return null;
    }
}