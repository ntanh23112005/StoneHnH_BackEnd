package com.stonehnh.payment.controller;

import com.stonehnh.common.handler.ApiResponse; // Assuming this class is available
import com.stonehnh.payment.dto.request.CreationPaymentDto;
import com.stonehnh.payment.dto.response.PaymentResponseDto;
import com.stonehnh.payment.dto.response.PaymentWithDetailDto;
import com.stonehnh.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Lấy danh sách tất cả các thanh toán.
     * GET /api/v1/payments
     * @return ApiResponse chứa danh sách PaymentResponseDto
     */
    @GetMapping
    public ApiResponse<Object> getAllPayments() {
        List<PaymentResponseDto> payments = paymentService.getAllPayments();
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách thanh toán thành công.")
                .data(payments)
                .build();
    }

    /**
     * Tìm thanh toán theo ID.
     * GET /api/v1/payments/{paymentId}
     * @param paymentId ID của thanh toán
     * @return ApiResponse chứa PaymentResponseDto tương ứng
     */
    @GetMapping("/{paymentId}")
    public ApiResponse<Object> getPaymentById(@PathVariable String paymentId) {
        try {
            PaymentResponseDto payment = paymentService.findPaymentById(paymentId);
            return ApiResponse.builder()
                    .success(true)
                    .message("Lấy thông tin thanh toán thành công.")
                    .data(payment)
                    .build();
        } catch (NoSuchElementException e) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy thanh toán với ID: " + paymentId)
                    .data(null)
                    .build();
        }
    }

    /**
     * Tạo mới một thanh toán.
     * POST /api/v1/payments
     * @param request CreationPaymentDto chứa thông tin thanh toán mới
     * @return ApiResponse chứa số dòng bị ảnh hưởng
     */
    @PostMapping
    public ApiResponse<?> createPayment(@RequestBody CreationPaymentDto request) {
        return paymentService.createPayment(request);
    }

    /**
     * Cập nhật thông tin thanh toán theo ID.
     * PUT /api/v1/payments/{paymentId}
     * @param paymentId ID của thanh toán cần cập nhật
     * @param request CreationPaymentDto chứa thông tin cập nhật
     * @return ApiResponse với số dòng bị ảnh hưởng
     */
    @PutMapping("/{paymentId}")
    public ApiResponse<Object> updatePayment(@PathVariable String paymentId, @RequestBody CreationPaymentDto request) {
        int rowsAffected = paymentService.updatePayment(paymentId, request);
        if (rowsAffected > 0) {
            return ApiResponse.builder()
                    .success(true)
                    .message("Cập nhật thanh toán thành công.")
                    .data(rowsAffected)
                    .build();
        } else {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy thanh toán để cập nhật hoặc không có thay đổi.")
                    .data(0)
                    .build();
        }
    }

    /**
     * Xóa thanh toán theo ID.
     * DELETE /api/v1/payments/{paymentId}
     * @param paymentId ID của thanh toán cần xóa
     * @return ApiResponse với số dòng bị ảnh hưởng
     */
    @DeleteMapping("/{paymentId}")
    public ApiResponse<Object> deletePayment(@PathVariable String paymentId) {
        int rowsAffected = paymentService.deletePayment(paymentId);
        if (rowsAffected > 0) {
            return ApiResponse.builder()
                    .success(true)
                    .message("Xóa thanh toán thành công.")
                    .data(rowsAffected)
                    .build();
        } else {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy thanh toán để xóa.")
                    .data(0)
                    .build();
        }
    }

    @GetMapping("/by-customer/{customerId}")
    public ApiResponse<Object> getPaymentsByCustomerId(@PathVariable String customerId) {
        List<PaymentWithDetailDto> result = paymentService.findPaymentsByCustomerId(customerId);
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách thanh toán theo khách hàng thành công.")
                .data(result)
                .build();
    }
}