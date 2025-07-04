package com.stonehnh.booking.controller;

import com.stonehnh.booking.converter.BookingDetailConverter;
import com.stonehnh.booking.dto.request.CreationBookingDetailDto;
import com.stonehnh.booking.dto.response.BookingDetailResponseDto;
import com.stonehnh.booking.service.BookingDetailService;
import com.stonehnh.common.handler.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking-details") // Thêm version nếu cần
public class BookingDetailController {

    private final BookingDetailService bookingDetailService;

    @Autowired
    public BookingDetailController(BookingDetailService bookingDetailService) {
        this.bookingDetailService = bookingDetailService;
    }

    // ✅ 1. Tạo mới booking detail
    @PostMapping
    public ApiResponse<Object> createBookingDetail(@RequestBody CreationBookingDetailDto dto) {
        int result = bookingDetailService.createNewBookingDetail(dto);
        return ApiResponse.builder()
                .success(true)
                .message("Tạo chi tiết đơn đặt phòng thành công.")
                .data(result)
                .build();
    }

    // ✅ 2. Lấy danh sách tất cả booking detail
    @GetMapping
    public ApiResponse<Object> getAllBookingDetails() {
        List<BookingDetailResponseDto> list = bookingDetailService.getAllBookingDetails();
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách chi tiết đặt phòng thành công.")
                .data(list)
                .build();
    }

    // ✅ 3. Lấy một booking detail theo ID
    @GetMapping("/{id}")
    public ApiResponse<Object> getBookingDetailById(@PathVariable String id) {
        BookingDetailResponseDto detail = bookingDetailService.findBookingDetailById(id);
        return ApiResponse.builder()
                .success(true)
                .message("Lấy chi tiết đặt phòng thành công.")
                .data(detail)
                .build();
    }

    // ✅ 4. Cập nhật booking detail
    @PutMapping("/{id}")
    public ApiResponse<Object> updateBookingDetail(@PathVariable String id, @RequestBody CreationBookingDetailDto dto) {
        int result = bookingDetailService.updateBookingDetail(id, BookingDetailConverter.toEntity(dto));
        return ApiResponse.builder()
                .success(true)
                .message("Cập nhật chi tiết đặt phòng thành công.")
                .data(result)
                .build();
    }

    // ✅ 5. Xoá booking detail
    @DeleteMapping("/{id}")
    public ApiResponse<Object> deleteBookingDetail(@PathVariable String id) {
        int result = bookingDetailService.deleteBookingDetailById(id);
        return ApiResponse.builder()
                .success(true)
                .message("Xoá chi tiết đặt phòng thành công.")
                .data(result)
                .build();
    }
}
