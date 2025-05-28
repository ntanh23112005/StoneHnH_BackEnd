package com.stonehnh.homestay.converter;

import com.stonehnh.homestay.dto.request.CreationHomestayImagesDto;
import com.stonehnh.homestay.dto.response.HomestayImagesResponseDto;
import com.stonehnh.homestay.entity.HomestayImages;

import java.util.ArrayList;
import java.util.List;

public class HomestayImagesConverter {
    /**
     * Convert 1 entity HomestayImages -> DTO
     */
    public static HomestayImagesResponseDto toDto(HomestayImages entity) {
        return HomestayImagesResponseDto.builder()
                .id(entity.getId())
                .homestayId(entity.getHomestayId())
                .homestayImage(entity.getHomestayImage())
                .imageFor(entity.getImageFor())
                .build();
    }

    /**
     * Convert List entity HomestayImages -> List DTO
     */
    public static List<HomestayImagesResponseDto> toDtoList(List<HomestayImages> entityList) {
        List<HomestayImagesResponseDto> dtoList = new ArrayList<>();
        for (HomestayImages entity : entityList) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

    /**
     * Convert DTO HomestayImageDto -> entity HomestayImages
     */
    public static HomestayImages toEntity(CreationHomestayImagesDto dto) {
        HomestayImages entity = new HomestayImages();
        entity.setId(dto.getId());
        entity.setHomestayId(dto.getHomestayId());
        entity.setHomestayImage(dto.getHomestayImage());
        entity.setImageFor(dto.getImageFor());
        return entity;
    }

    /**
     * Convert List DTO -> List entity
     */
    public static List<HomestayImages> toEntityList(List<CreationHomestayImagesDto> dtoList) {
        List<HomestayImages> entityList = new ArrayList<>();
        for (CreationHomestayImagesDto dto : dtoList) {
            entityList.add(toEntity(dto));
        }
        return entityList;
    }
}
