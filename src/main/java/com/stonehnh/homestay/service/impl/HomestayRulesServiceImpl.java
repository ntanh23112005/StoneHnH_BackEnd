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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HomestayRulesServiceImpl implements HomestayRulesService {

    private final HomestayRulesMapper homestayRulesMapper;

    public HomestayRulesServiceImpl(HomestayRulesMapper homestayRulesMapper) {
        this.homestayRulesMapper = homestayRulesMapper;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public HomestayRulesResponseDto getRulesByHomestayId(String homestayId) {
        try {
            HomestayRule homestayRule = homestayRulesMapper.findRulesByHomestayId(homestayId);

            if (homestayRule == null) {
                throw new AppException(ErrorCode.HOMESTAY_RULE_NOT_FOUND);
            }

            return HomestayRulesConverter.toDto(homestayRule);

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // hoặc log.error("Lỗi khi lấy quy định homestay", e);
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createRule(CreationHomestayRulesDto dto) {
        try {
            HomestayRule entity = HomestayRulesConverter.toEntity(dto);

            int rowsAffected = homestayRulesMapper.insertRule(entity);
            if (rowsAffected == 0) {
                throw new AppException(ErrorCode.HOMESTAY_RULE_CREATE_FAILED);
            }

            return rowsAffected;

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // hoặc log.error("Lỗi khi tạo quy định homestay", e);
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRule(int id, CreationHomestayRulesDto dto) {
        try {
            boolean exists = homestayRulesMapper.existsRuleById(id);
            if (!exists) {
                throw new AppException(ErrorCode.HOMESTAY_RULE_NOT_FOUND);
            }

            HomestayRule entity = HomestayRulesConverter.toEntity(dto);
            entity.setId(id);

            int rowsAffected = homestayRulesMapper.updateRule(entity);
            if (rowsAffected == 0) {
                throw new AppException(ErrorCode.HOMESTAY_RULE_CREATE_FAILED); // Gợi ý dùng mã lỗi này nếu không có lỗi update riêng
            }

            return rowsAffected;

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // hoặc log.error(...) nếu có logging
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRule(int id) {
        try {
            boolean exists = homestayRulesMapper.existsRuleById(id);
            if (!exists) {
                throw new AppException(ErrorCode.HOMESTAY_RULE_NOT_FOUND);
            }

            int rowsAffected = homestayRulesMapper.deleteRuleById(id);
            if (rowsAffected == 0) {
                throw new AppException(ErrorCode.HOMESTAY_RULE_NOT_FOUND); // Gợi ý mã lỗi mới
            }

            return rowsAffected;

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // Hoặc log.error(...) nếu dùng logger
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public HomestayRulesResponseDto findRuleById(int id) {
        try {
            HomestayRule rule = homestayRulesMapper.findRuleById(id);
            if (rule == null) {
                throw new AppException(ErrorCode.HOMESTAY_RULE_NOT_FOUND);
            }

            return HomestayRulesConverter.toDto(rule);

        } catch (AppException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace(); // hoặc log.error("Lỗi tìm rule với ID {}: {}", id, e.getMessage(), e);
            throw new AppException(ErrorCode.SYSTEM_ERROR);
        }
    }
}