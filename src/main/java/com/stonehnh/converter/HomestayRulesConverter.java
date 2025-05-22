package com.stonehnh.converter;

import com.stonehnh.dto.request.CreationHomestayImagesDto;
import com.stonehnh.dto.request.CreationHomestayRulesDto;
import com.stonehnh.dto.response.HomestayRulesResponseDto;
import com.stonehnh.entity.HomestayRules;

import java.util.ArrayList;
import java.util.List;

public class HomestayRulesConverter {
    /**
     * Convert 1 entity -> DTO
     */
    public static HomestayRulesResponseDto toDto(HomestayRules entity) {
        return HomestayRulesResponseDto.builder()
                .id(entity.getId())
                .homestayId(entity.getHomestayId())
                .ruleText(entity.getRuleText())
                .policyText(entity.getPolicyText())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    /**
     * Convert List entity -> List DTO
     */
    public static List<HomestayRulesResponseDto> toDtoList(List<HomestayRules> entities) {
        List<HomestayRulesResponseDto> dtoList = new ArrayList<>();
        for (HomestayRules entity : entities) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

    /**
     * Convert 1 DTO -> entity
     */
    public static HomestayRules toEntity(CreationHomestayRulesDto dto) {
        HomestayRules entity = new HomestayRules();
        entity.setId(dto.getId());
        entity.setHomestayId(dto.getHomestayId());
        entity.setRuleText(dto.getRuleText());
        entity.setPolicyText(dto.getPolicyText());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }

    /**
     * Convert List DTO -> List entity
     */
    public static List<HomestayRules> toEntityList(List<CreationHomestayRulesDto> dtoList) {
        List<HomestayRules> entityList = new ArrayList<>();
        for (CreationHomestayRulesDto dto : dtoList) {
            entityList.add(toEntity(dto));
        }
        return entityList;
    }
}
