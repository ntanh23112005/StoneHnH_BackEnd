package com.stonehnh.admin.controller;

import com.stonehnh.admin.dto.response.HomestayDto;
import com.stonehnh.admin.service.AdminService;
import com.stonehnh.booking.dto.response.BookingWithDetailDto;
import com.stonehnh.booking.service.BookingService;
import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.customer.dto.request.CreationCustomerWithRoles;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.mapper.CustomerMapper;
import com.stonehnh.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final BookingService bookingService;

    public AdminController(AdminService adminService, CustomerService customerService, CustomerMapper customerMapper, BookingService bookingService) {
        this.adminService = adminService;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.bookingService = bookingService;
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

    @GetMapping("/homestays")
    public ApiResponse<Object> getAllHomestays(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        List<HomestayDto> homestayList = adminService.getAllHomestays(limit, offset);
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách homestay thành công")
                .data(homestayList)
                .build();
    }

    @PutMapping("/homestays/{homestayId}/status")
    public ApiResponse<Object> updateHomestayStatus(
            @PathVariable String homestayId,
            @RequestBody Map<String, Boolean> payload) {

        Boolean status = payload.get("status");
        adminService.updateHomestayStatus(homestayId, status);

        return ApiResponse.builder()
                .success(true)
                .message("Cập nhật trạng thái homestay thành công")
                .data(null)
                .build();
    }

    @GetMapping("/bookings")
    public ApiResponse<Object> getAllBookings() {
        List<BookingWithDetailDto> bookings = bookingService.getAllBookingsWithDetails();
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách đơn đặt phòng thành công.")
                .data(bookings)
                .build();
    }
}
