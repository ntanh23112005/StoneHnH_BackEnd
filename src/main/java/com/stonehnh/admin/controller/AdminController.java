package com.stonehnh.admin.controller;

import com.stonehnh.admin.service.AdminService;
import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.customer.dto.request.CreationCustomerWithRoles;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.mapper.CustomerMapper;
import com.stonehnh.customer.service.CustomerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public AdminController(AdminService adminService, CustomerService customerService, CustomerMapper customerMapper) {
        this.adminService = adminService;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/stats")
    public ApiResponse<Object> getStatsForOverviewPage() {
        return ApiResponse.builder()
                .message("Lấy stats thành công")
                .success(true)
                .data(adminService.getTotalStats())
                .build();
    }

    @GetMapping("/monthly-revenue")
    public ApiResponse<Object> getMonthlyRevenue(
            @RequestParam(required = false) Integer year) {

        if (year == null || year == 0) {
            year = LocalDate.now().getYear();
        }
        
        return ApiResponse.builder()
                .message("Lấy doanh thu theo năm thành công")
                .success(true)
                .data(adminService.getMonthlyRevenueByYear(year))
                .build();
    }

    @GetMapping("/bookings-ratio")
    public ApiResponse<Object> getBookingRatio() {
        return ApiResponse.builder()
                .message("Lấy tỉ lệ booking thành công")
                .success(true)
                .data(adminService.getBookingStatusRatio())
                .build();
    }

    @GetMapping("/users")
    public ApiResponse<Object> getUsers(@RequestParam(required = false) String email) {

        if (email != null && !email.isBlank()) {
            List<CustomerResponseDto> customerList = customerMapper.findCustomersByEmailLike(email);
            if (customerList != null) {
                return ApiResponse.builder()
                        .message("Tìm kiếm người dùng thành công")
                        .success(true)
                        .data(customerList) // Trả về list để đồng nhất FE
                        .build();
            } else {
                return ApiResponse.builder()
                        .message("Không tìm thấy người dùng với email: " + email)
                        .success(true)
                        .data(List.of())
                        .build();
            }
        }

        return ApiResponse.builder()
                .message("Lấy danh sách user thành công")
                .success(true)
                .data(adminService.getAllCustomersAndRoles())
                .build();
    }

    @PostMapping("/users")
    public ApiResponse<Object> addCustomer(@RequestBody CreationCustomerWithRoles creationCustomerDto) {
        return ApiResponse.builder()
                .message("Thêm mới người dùng thành công")
                .success(true)
                .data(customerService.createNewCustomerWithRole(creationCustomerDto.getCreationCustomer(), creationCustomerDto.getRoleIds()))
                .build();
    }

    @PutMapping("/users")
    public ApiResponse<Object> updateCustomer(@RequestBody CreationCustomerWithRoles updateDto) {
        System.out.println("ĐÃ vào Update");
        CustomerResponseDto updated = customerService.updateCustomerWithRole(updateDto.getCreationCustomer(), updateDto.getRoleIds());

        return ApiResponse.builder()
                .success(true)
                .message("Cập nhật người dùng thành công")
                .data(updated)
                .build();
    }

}
