package com.stonehnh.review.service;

import com.stonehnh.review.dto.request.CreationRateDetailDto;
import com.stonehnh.review.dto.response.RateResponseDto;
import com.stonehnh.review.entity.Rate;

import java.util.List;

public interface RateService {
    /**
     * Lấy danh sách tất cả Rate
     * @return Danh sách RateResponseDto
     */
    List<RateResponseDto> getAllRates();

    /**
     * Tạo mới 1 Rate
     * @param creationRateDetailDto đối tượng DTO chứa dữ liệu tạo mới
     * @return Số dòng bị ảnh hưởng
     */
    int createNewRate(CreationRateDetailDto creationRateDetailDto);

    /**
     * Cập nhật 1 Rate theo rateId
     * @param rateId mã Rate cần cập nhật
     * @param rate entity Rate chứa dữ liệu mới
     * @return Số dòng bị ảnh hưởng
     */
    int updateRate(String rateId, Rate rate);

    /**
     * Xóa 1 Rate theo rateId
     * @param rateId mã Rate cần xóa
     * @return Số dòng bị ảnh hưởng
     */
    int deleteRateById(String rateId);

    /**
     * Tìm 1 Rate theo rateId
     * @param rateId mã Rate cần tìm
     * @return RateResponseDto nếu tìm thấy, null nếu không
     */
    RateResponseDto findRateById(String rateId);
}
