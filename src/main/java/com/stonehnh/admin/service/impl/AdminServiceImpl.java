package com.stonehnh.admin.service.impl;

import com.stonehnh.admin.dto.response.BookingStatusDto;
import com.stonehnh.admin.dto.response.MonthlyRevenueDto;
import com.stonehnh.admin.dto.response.TotalStatsResponseDto;
import com.stonehnh.admin.mapper.AdminMapper;
import com.stonehnh.admin.service.AdminService;
import com.stonehnh.booking.service.BookingService;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.mapper.CustomerMapper;
import com.stonehnh.customer.service.CustomerService;
import com.stonehnh.homestay.service.HomestayService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final BookingService bookingService;
    private final CustomerService customerService;
    private final HomestayService homestayService;
    private final AdminMapper adminMapper;
    private final CustomerMapper customerMapper;

    public AdminServiceImpl(BookingService bookingService, CustomerService customerService, HomestayService homestayService, AdminMapper adminMapper, CustomerMapper customerMapper) {
        this.bookingService = bookingService;
        this.customerService = customerService;
        this.homestayService = homestayService;
        this.adminMapper = adminMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public TotalStatsResponseDto getTotalStats() {
        TotalStatsResponseDto totalStatsResponseDto = new TotalStatsResponseDto();
        totalStatsResponseDto.setTotalBookings(bookingService.countBookings());
        totalStatsResponseDto.setTotalPriceBooking(bookingService.getTotalPaymentStatus());
        totalStatsResponseDto.setTotalUsers(customerService.countAllCustomers());
        totalStatsResponseDto.setTotalHomestays(homestayService.countAllHomestays());
        return totalStatsResponseDto;
    }

    @Override
    public List<MonthlyRevenueDto> getMonthlyRevenueByYear(Integer year) {

        if(year <= 0 ){
            year = LocalDate.now().getYear();
        }

        return adminMapper.getMonthlyRevenue(year);
    }

    @Override
    public List<BookingStatusDto> getBookingStatusRatio() {
        return adminMapper.getBookingStatusRatio();
    }

    @Override
    public List<CustomerResponseDto> getAllCustomersAndRoles() {
        return customerMapper.getAllCustomerAndRoles();
    }
}
