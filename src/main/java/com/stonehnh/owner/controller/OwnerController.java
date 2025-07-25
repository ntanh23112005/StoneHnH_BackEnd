package com.stonehnh.owner.controller;

import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.owner.dto.request.CreationOwnerDto;
import com.stonehnh.owner.dto.request.RegisterOwnerRoleRequest;
import com.stonehnh.owner.dto.response.*;
import com.stonehnh.owner.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Lấy thông tin owner theo homestayId
     */
    @GetMapping("/homestay/{homestayId}")
    public ApiResponse<Object> getOwnersByHomestayId(@PathVariable String homestayId) {
        OwnerResponseDto response = ownerService.getOwnersByHomestayId(homestayId);
        return ApiResponse.builder()
                .success(true)
                .message("Lấy thông tin chủ sở hữu theo homestay thành công.")
                .data(response)
                .build();
    }

    /**
     * Tạo mới chủ sở hữu
     */
    @PostMapping
    public ApiResponse<Object> createOwner(@RequestBody CreationOwnerDto dto) {
        int result = ownerService.createOwner(dto);
        return ApiResponse.builder()
                .success(true)
                .message("Tạo chủ sở hữu thành công.")
                .data(result)
                .build();
    }

    /**
     * Cập nhật quyền sở hữu (ownership)
     */
    @PutMapping("/{customerId}")
    public ApiResponse<Object> updateOwner(@PathVariable String customerId,
                                           @RequestBody CreationOwnerDto dto) {
        int result = ownerService.updateOwnership(customerId, dto);
        return ApiResponse.builder()
                .success(true)
                .message("Cập nhật quyền sở hữu thành công.")
                .data(result)
                .build();
    }

    /**
     * Xoá chủ sở hữu theo customerId và homestayId
     */
    @DeleteMapping
    public ApiResponse<Object> deleteOwner(@RequestParam String customerId,
                                           @RequestParam String homestayId) {
        int result = ownerService.deleteOwner(customerId, homestayId);
        return ApiResponse.builder()
                .success(true)
                .message("Xoá chủ sở hữu thành công.")
                .data(result)
                .build();
    }

    @PostMapping("/register-role")
    public ResponseEntity<Void> registerOwnerRole(@RequestBody RegisterOwnerRoleRequest request) {
        ownerService.registerAsOwner(request.getCustomerId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/statistics/{customerId}")
    public ResponseEntity<OwnerStatisticsDto> getStatistics(@PathVariable String customerId) {
        OwnerStatisticsDto statistics = ownerService.getStatistics(customerId);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/bookings/details/{customerId}")
    public ResponseEntity<List<OwnerBookingDetailDto>> getAllBookingDetails(@PathVariable String customerId) {
        List<OwnerBookingDetailDto> details = ownerService.getAllBookingDetails(customerId);
        return ResponseEntity.ok(details);
    }

    @GetMapping("/bookings/{customerId}")
    public ResponseEntity<List<OwnerBookingDto>> getAllBookings(@PathVariable String customerId) {
        List<OwnerBookingDto> bookings = ownerService.getAllBookings(customerId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{customerId}/monthly-revenue")
    public List<MonthlyRevenueOwnerDto> getMonthlyRevenue(
            @PathVariable String customerId,
            @RequestParam int year) {
        return ownerService.getMonthlyRevenue(customerId, year);
    }

    @GetMapping("/{customerId}/homestays")
    public List<OwnerHomestayDto> getOwnerHomestays(@PathVariable String customerId) {
        return ownerService.getOwnerHomestays(customerId);
    }
}
