package com.stonehnh.admin.service;

import com.stonehnh.admin.dto.response.BookingStatusDto;
import com.stonehnh.admin.dto.response.HomestayDto;
import com.stonehnh.admin.dto.response.MonthlyRevenueDto;
import com.stonehnh.admin.dto.response.TotalStatsResponseDto;
import com.stonehnh.common.dto.PageDTO;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.homestay.dto.response.HomestayHomePageAdminResponseDto;
import com.stonehnh.homestay.dto.response.HomestayHomePageResponseDto;

import java.util.List;

public interface AdminService {
    /**
     * Lấy dữ liệu tổng quan cho admin dashboard
     */
    TotalStatsResponseDto getTotalStats();

    /**
     * Lấy doanh thu từng tháng theo năm
     * @param year năm cần lấy
     * @return MonthlyRevenueDto ( month, revenue)
     */
    List<MonthlyRevenueDto> getMonthlyRevenueByYear(Integer year);

    /**
     * Lấy số lượng booking theo trạng thái
     * @return BookingStatusDto (status, value)
     */
    List<BookingStatusDto> getBookingStatusRatio();

    List<CustomerResponseDto> getAllCustomersAndRoles();

    /**
     * Cập nhật trạng thái homestay
     * @param homestayId id homestay
     * @param status trạng thái mới
     */
    void updateHomestayStatus(String homestayId, boolean status);

    PageDTO<HomestayHomePageAdminResponseDto> getHomestays(int page, int size, String name, String status);
}
