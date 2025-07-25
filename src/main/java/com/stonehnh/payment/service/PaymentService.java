package com.stonehnh.payment.service;

import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.payment.dto.request.CreationPaymentDto;
import com.stonehnh.payment.dto.response.PaymentResponseDto;
import com.stonehnh.payment.dto.response.PaymentWithDetailDto;

import java.util.List;

public interface PaymentService {
    /**
     * Lấy danh sách tất cả payment
     * @return Danh sách PaymentDto
     */
    List<PaymentResponseDto> getAllPayments();

    /**
     * Tìm payment theo ID
     * @param paymentId ID của payment
     * @return PaymentDto tương ứng
     */
    PaymentResponseDto findPaymentById(String paymentId);

    /**
     * Tìm danh sách payment theo bookingId
     * @param bookingId ID của booking
     * @return Danh sách PaymentDto
     */
//    List<PaymentResponseDto> findPaymentsByBookingId(String bookingId);

    /**
     * Tạo mới payment
     * @param paymentDto thông tin payment mới
     * @return Số dòng bị ảnh hưởng
     */
//    int createPayment(CreationPaymentDto paymentDto);

    /**
     * Cập nhật thông tin payment theo ID
     * @param paymentId ID của payment
     * @param paymentDto thông tin mới
     * @return Số dòng bị ảnh hưởng
     */
    int updatePayment(String paymentId, CreationPaymentDto paymentDto);

    /**
     * Xóa payment theo ID
     * @param paymentId ID của payment
     * @return Số dòng bị ảnh hưởng
     */
    int deletePayment(String paymentId);

    /**
     * Lấy danh sách payment theo customerId
     * @param customerId ID của customer
     * @return Danh sách PaymentDto
     */
    List<PaymentWithDetailDto> findPaymentsByCustomerId(String customerId);

    ApiResponse<?> createPayment(CreationPaymentDto dto);

}
