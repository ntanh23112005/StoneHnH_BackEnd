package com.stonehnh.service.impl;

import com.stonehnh.converter.HomestayImagesConverter;
import com.stonehnh.dto.request.CreationHomestayImagesDto;
import com.stonehnh.dto.response.HomestayImagesResponseDto;
import com.stonehnh.entity.HomestayImages;
import com.stonehnh.enums.ErrorCode;
import com.stonehnh.exception.AppException;
import com.stonehnh.mapper.HomestayImagesMapper;
import com.stonehnh.service.HomestayImageService;

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
            throw new AppException(ErrorCode.IMAGE_NOT_FOUND);
        }

        HomestayImages entity = HomestayImagesConverter.toEntity(dto);
        entity.setId(id);
        return homestayImagesMapper.updateImage(entity);
    }

    @Override
    public int deleteImage(int id) {
        boolean exists = homestayImagesMapper.existsImageById(id);
        if (!exists) {
            throw new AppException(ErrorCode.IMAGE_NOT_FOUND);
        }

        return homestayImagesMapper.deleteImageById(id);
    }
}