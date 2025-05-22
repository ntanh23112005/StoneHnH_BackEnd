package com.stonehnh.converter;

import com.stonehnh.dto.request.CreationOwnerDto;
import com.stonehnh.dto.response.OwnerResponseDto;
import com.stonehnh.entity.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerConverter {
    /**
     * Convert entity -> DTO
     */
    public static OwnerResponseDto toDto(Owner entity) {
        return OwnerResponseDto.builder()
                .customerId(entity.getCustomerId())
                .homestayId(entity.getHomestayId())
                .percentageOwn(entity.getPercentageOwn())
                .build();
    }

    /**
     * Convert list entity -> list DTO
     */
    public static List<OwnerResponseDto> toDtoList(List<Owner> entities) {
        List<OwnerResponseDto> dtoList = new ArrayList<>();
        for (Owner entity : entities) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

    /**
     * Convert DTO -> entity
     */
    public static Owner toEntity(CreationOwnerDto dto) {
        Owner entity = new Owner();
        entity.setCustomerId(dto.getCustomerId());
        entity.setHomestayId(dto.getHomestayId());
        entity.setPercentageOwn(dto.getPercentageOwn());
        return entity;
    }

    /**
     * Convert list DTO -> list entity
     */
    public static List<Owner> toEntityList(List<CreationOwnerDto> dtoList) {
        List<Owner> entityList = new ArrayList<>();
        for (CreationOwnerDto dto : dtoList) {
            entityList.add(toEntity(dto));
        }
        return entityList;
    }
}
