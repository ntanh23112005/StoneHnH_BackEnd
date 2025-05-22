package com.stonehnh.service;

import com.stonehnh.dto.*;
import com.stonehnh.dto.request.CreationHomestayDto;
import com.stonehnh.dto.response.HomestayResponseDto;
import com.stonehnh.entity.Homestay;

import java.util.List;

public interface HomestayService {

    /**
     * Lấy danh sách tất cả homestay
     * @return Danh sách homestay dưới dạng DTO
     */
    List<HomestayResponseDto> getAllHomestays();

    /**
     * Tạo mới một homestay
     * @param homestayDto dữ liệu homestay từ frontend
     * @return Số dòng bị ảnh hưởng
     */
    int createHomestay(CreationHomestayDto homestayDto);

    /**
     * Cập nhật homestay theo ID
     * @param homestayId ID cần cập nhật
     * @param homestay entity mới
     * @return Số dòng bị ảnh hưởng
     */
    int updateHomestay(String homestayId, CreationHomestayDto homestayDto);

    /**
     * Xóa homestay theo ID
     * @param homestayId ID cần xóa
     * @return Số dòng bị ảnh hưởng
     */
    int deleteHomestay(String homestayId);

    /**
     * Tìm homestay theo ID
     * @param homestayId ID cần tìm
     * @return Thông tin homestay dưới dạng DTO
     */
    HomestayResponseDto findHomestayById(String homestayId);
}