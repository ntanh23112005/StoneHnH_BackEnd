package com.stonehnh.admin.service;

import com.stonehnh.admin.dto.response.BookingStatusDto;
import com.stonehnh.admin.dto.response.MonthlyRevenueDto;
import com.stonehnh.admin.dto.response.TotalStatsResponseDto;
import com.stonehnh.customer.dto.response.CustomerResponseDto;

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
}
