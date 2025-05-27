package com.stonehnh.service;

import com.stonehnh.dto.response.RateResponseDto;

import java.util.List;

public interface RateService {
/**
 * Lấy danh sách tất cả customers
 * @return Danh sách customers
 */
List<RateResponseDto> getAllRate();
}
