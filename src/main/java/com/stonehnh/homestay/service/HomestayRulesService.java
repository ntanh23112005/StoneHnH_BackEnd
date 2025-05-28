package com.stonehnh.homestay.service;

import com.stonehnh.homestay.dto.request.CreationHomestayRulesDto;
import com.stonehnh.homestay.dto.response.HomestayRulesResponseDto;

import java.util.List;

public interface HomestayRulesService {
    /**
     * Lấy tất cả nội quy homestay
     * @return Danh sách HomestayRuleDto
     */
//    List<HomestayRulesResponseDto> getAllRules();

    /**
     * Lấy danh sách nội quy theo homestayId
     * @param homestayId ID của homestay
     * @return Danh sách HomestayRuleDto
     */
    List<HomestayRulesResponseDto> getRulesByHomestayId(String homestayId);

    /**
     * Thêm mới nội quy cho homestay
     * @param ruleDto Thông tin nội quy
     * @return Số dòng bị ảnh hưởng
     */
    int createRule(CreationHomestayRulesDto ruleDto);

    /**
     * Cập nhật nội quy theo ID
     * @param id ID nội quy
     * @param ruleDto Dữ liệu cập nhật
     * @return Số dòng bị ảnh hưởng
     */
    int updateRule(int id, CreationHomestayRulesDto ruleDto);

    /**
     * Xóa nội quy theo ID
     * @param id ID nội quy cần xóa
     * @return Số dòng bị ảnh hưởng
     */
    int deleteRule(int id);

    /**
     * Tìm nội quy theo ID
     * @param id ID nội quy
     * @return HomestayRuleDto
     */
    HomestayRulesResponseDto findRuleById(int id);
}
