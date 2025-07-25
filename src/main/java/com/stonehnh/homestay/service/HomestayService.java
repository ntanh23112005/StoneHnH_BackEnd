package com.stonehnh.homestay.service;

import com.stonehnh.common.dto.PageDTO;
import com.stonehnh.homestay.dto.request.CreationHomestayDto;
import com.stonehnh.homestay.dto.response.HomestayDetailResponseDto;
import com.stonehnh.homestay.dto.response.HomestayHomePageResponseDto;
import com.stonehnh.homestay.dto.response.HomestayResponseDto;

import java.util.List;

public interface HomestayService {

    /**
     * Lấy danh sách tất cả homestay
     * @return Danh sách homestay dưới dạng DTO
     */
    List<HomestayResponseDto> getAllHomestays();

    /**
     * Tạo mới một homestay
     *
     * @param homestayDto dữ liệu homestay từ frontend
     * @return Số dòng bị ảnh hưởng
     */
    String createHomestay(CreationHomestayDto homestayDto);

    /**
     * Cập nhật homestay theo ID
     * @param homestayId ID cần cập nhật
     * @param homestayDto entity mới
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

    /**
     * Lấy danh sách homestay cho homepage
     *
     * @return List homestay cho homepage
     * */
    PageDTO<HomestayHomePageResponseDto> getHomestayHomePage(String category, String areaAddress, Integer maxCustomer, int page, int size);

    /**
     * Lấy homestay cho detail Page
     *
     * @return homestay cho detail Page
     * */
    HomestayDetailResponseDto getHomestayDetail(String homestayId);

    /**
     * Đếm toàn bộ số lượng homestay
     */
    int countAllHomestays();
}