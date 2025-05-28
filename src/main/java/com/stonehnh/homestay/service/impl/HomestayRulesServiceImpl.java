package com.stonehnh.homestay.service.impl;

import com.stonehnh.homestay.converter.HomestayRulesConverter;
import com.stonehnh.homestay.dto.request.CreationHomestayRulesDto;
import com.stonehnh.homestay.dto.response.HomestayRulesResponseDto;
import com.stonehnh.homestay.entity.HomestayRule;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.homestay.service.HomestayRulesService;
import com.stonehnh.homestay.mapper.HomestayRulesMapper;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class HomestayRulesServiceImpl implements HomestayRulesService {

        private final HomestayRulesMapper homestayRulesMapper;

        public HomestayRulesServiceImpl(HomestayRulesMapper homestayRulesMapper) {
            this.homestayRulesMapper = homestayRulesMapper;
        }

        @Override
        public List<HomestayRulesResponseDto> getRulesByHomestayId(String homestayId) {
            List<HomestayRule> rules = homestayRulesMapper.findRulesByHomestayId(homestayId);
            return HomestayRulesConverter.toDtoList(rules);
        }

        @Override
        public int createRule(CreationHomestayRulesDto dto) {
            HomestayRule entity = HomestayRulesConverter.toEntity(dto);
            return homestayRulesMapper.insertRule(entity);
        }

        @Override
        public int updateRule(int id, CreationHomestayRulesDto dto) {
            boolean exists = homestayRulesMapper.existsRuleById(id);
            if (!exists) {
                throw new AppException(ErrorCode.HOMESTAY_RULE_NOT_FOUND);
            }

            HomestayRule entity = HomestayRulesConverter.toEntity(dto);
            entity.setId(id);
            return homestayRulesMapper.updateRule(entity);
        }

        @Override
        public int deleteRule(int id) {
            boolean exists = homestayRulesMapper.existsRuleById(id);
            if (!exists) {
                throw new AppException(ErrorCode.HOMESTAY_RULE_NOT_FOUND);
            }

            return homestayRulesMapper.deleteRuleById(id);
        }

        @Override
        public HomestayRulesResponseDto findRuleById(int id) {
            HomestayRule rule = homestayRulesMapper.findRuleById(id);
            if (rule == null) {
                throw new AppException(ErrorCode.HOMESTAY_RULE_NOT_FOUND);
            }
            return HomestayRulesConverter.toDto(rule);
        }
    }
