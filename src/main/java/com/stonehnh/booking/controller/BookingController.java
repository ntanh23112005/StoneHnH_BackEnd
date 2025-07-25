package com.stonehnh.booking.controller;

import com.stonehnh.booking.dto.request.CreationBookingWrapperDto;
import com.stonehnh.booking.dto.response.BookingResponseDto;
import com.stonehnh.booking.dto.response.BookingWithDetailDto;
import com.stonehnh.booking.service.BookingService;
import com.stonehnh.common.handler.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings") // thêm v1 nếu cần versioning
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Tạo mới một booking
//    @PostMapping
//    public ApiResponse<Object> createBooking(@RequestBody CreationBookingDto dto) {
//        int result = bookingService.createNewBooking(dto);
//        return ApiResponse.builder()
//                .success(true)
//                .message("Tạo đơn đặt phòng thành công.")
//                .data(result)
//                .build();
//    }

    // Lấy danh sách tất cả booking
    @GetMapping
    public ApiResponse<Object> getAllBookings() {
        List<BookingResponseDto> bookings = bookingService.getAllBookings();
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách đơn đặt phòng thành công.")
                .data(bookings)
                .build();
    }

    // Lấy booking theo ID
    @GetMapping("/{id}")
    public ApiResponse<Object> getBookingById(@PathVariable String id) {
        BookingResponseDto booking = bookingService.findBookingByBookingId(id);
        return ApiResponse.builder()
                .success(true)
                .message("Lấy thông tin đơn đặt phòng thành công.")
                .data(booking)
                .build();
    }

    // Xoá booking theo ID
    @DeleteMapping("/{id}")
    public ApiResponse<Object> deleteBooking(@PathVariable String id) {
        bookingService.deleteBookingById(id);
        return ApiResponse.builder()
                .success(true)
                .message("Xoá đơn đặt phòng thành công.")
                .data(null)
                .build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createBookingWithDetail(
            @RequestBody CreationBookingWrapperDto request) {
        return ResponseEntity.ok(
                bookingService.createBookingWithDetail(
                        request.getBooking(),
                        request.getBookingDetail()
                )
        );
    }

    @PatchMapping("/{id}/accept-payment")
    public ApiResponse<Object> acceptPayment(@PathVariable String id) {
        int result = bookingService.updatePaymentStatus(id, 1);
        return ApiResponse.builder()
                .success(result > 0)
                .message(result > 0 ? "Đã chấp nhận đơn hàng." : "Không tìm thấy booking.")
                .data(result)
                .build();
    }
}
