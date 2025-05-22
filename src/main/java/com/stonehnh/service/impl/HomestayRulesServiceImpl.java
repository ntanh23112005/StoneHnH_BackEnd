package com.stonehnh.service.impl;

import com.stonehnh.converter.HomestayRulesConverter;
import com.stonehnh.dto.request.CreationHomestayRulesDto;
import com.stonehnh.dto.response.HomestayRulesResponseDto;
import com.stonehnh.entity.HomestayRules;
import com.stonehnh.enums.ErrorCode;
import com.stonehnh.exception.AppException;
import com.stonehnh.mapper.HomestayRulesMapper;
import com.stonehnh.service.HomestayRulesService;
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
            List<HomestayRules> rules = homestayRulesMapper.findRulesByHomestayId(homestayId);
            return HomestayRulesConverter.toDtoList(rules);
        }

        @Override
        public int createRule(CreationHomestayRulesDto dto) {
            HomestayRules entity = HomestayRulesConverter.toEntity(dto);
            return homestayRulesMapper.insertRule(entity);
        }

        @Override
        public int updateRule(int id, CreationHomestayRulesDto dto) {
            boolean exists = homestayRulesMapper.existsRuleById(id);
            if (!exists) {
                throw new AppException(ErrorCode.RULE_NOT_FOUND);
            }

            HomestayRules entity = HomestayRulesConverter.toEntity(dto);
            entity.setId(id);
            return homestayRulesMapper.updateRule(entity);
        }

        @Override
        public int deleteRule(int id) {
            boolean exists = homestayRulesMapper.existsRuleById(id);
            if (!exists) {
                throw new AppException(ErrorCode.RULE_NOT_FOUND);
            }

            return homestayRulesMapper.deleteRuleById(id);
        }

        @Override
        public HomestayRulesResponseDto findRuleById(int id) {
            HomestayRules rule = homestayRulesMapper.findRuleById(id);
            if (rule == null) {
                throw new AppException(ErrorCode.RULE_NOT_FOUND);
            }
            return HomestayRulesConverter.toDto(rule);
        }
    }
