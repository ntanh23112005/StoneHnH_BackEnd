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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HomestayImagesServiceImpl implements HomestayImageService {

    private final HomestayImagesMapper homestayImagesMapper;

    public HomestayImagesServiceImpl(HomestayImagesMapper homestayImagesMapper) {
        this.homestayImagesMapper = homestayImagesMapper;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<HomestayImagesResponseDto> getImagesByHomestayId(String homestayId) {
        try {
            List<HomestayImages> images = homestayImagesMapper.findImagesByHomestayId(homestayId);

            // Optional: Nếu muốn báo lỗi khi không có ảnh
            // if (images == null || images.isEmpty()) {
            //     throw new AppException(ErrorCode.HOMESTAY_IMAGE_NOT_FOUND);
            // }

            return HomestayImagesConverter.toDtoList(images);

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // hoặc log.error("Lỗi khi lấy ảnh homestay", e);
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createImage(CreationHomestayImagesDto dto) {
        try {
            HomestayImages entity = HomestayImagesConverter.toEntity(dto);

            int rowsAffected = homestayImagesMapper.insertImage(entity);
            if (rowsAffected == 0) {
                throw new AppException(ErrorCode.HOMESTAY_IMAGE_UPLOAD_FAILED);
            }

            return rowsAffected;

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // hoặc log.error("Lỗi khi tải ảnh homestay", e);
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateImage(int id, CreationHomestayImagesDto dto) {
        try {
            boolean exists = homestayImagesMapper.existsImageById(id);
            if (!exists) {
                throw new AppException(ErrorCode.HOMESTAY_IMAGE_NOT_FOUND);
            }

            HomestayImages entity = HomestayImagesConverter.toEntity(dto);
            entity.setId(id);

            int rowsAffected = homestayImagesMapper.updateImage(entity);
            if (rowsAffected == 0) {
                throw new AppException(ErrorCode.HOMESTAY_IMAGE_UPLOAD_FAILED); // Gợi ý thêm mã lỗi này
            }

            return rowsAffected;

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // hoặc dùng log.error(...) nếu có
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteImage(int id) {
        try {
            boolean exists = homestayImagesMapper.existsImageById(id);
            if (!exists) {
                throw new AppException(ErrorCode.HOMESTAY_IMAGE_NOT_FOUND);
            }

            int rowsAffected = homestayImagesMapper.deleteImageById(id);
            if (rowsAffected == 0) {
                throw new AppException(ErrorCode.HOMESTAY_IMAGE_NOT_FOUND); // Gợi ý thêm mã lỗi này
            }

            return rowsAffected;

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // hoặc log.error(...) nếu dùng logging
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }


    @Override
    public HomestayImagesResponseDto findImageById(int id) {
        return null;
    }
}