package com.stonehnh.verification.controller;

import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.verification.dto.request.CreationVerificationDto;
import com.stonehnh.verification.dto.response.VerificationResponseDto;
import com.stonehnh.verification.service.VerificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/v1/verifications")
//public class VerificationController {
//
//    private final VerificationService verificationService;
//
//    public VerificationController(VerificationService verificationService) {
//        this.verificationService = verificationService;
//    }
//
//    // ✅ Lấy danh sách tất cả mã xác thực
//    @GetMapping
//    public ApiResponse<Object> getAll() {
//        List<VerificationResponseDto> list = verificationService.findAll();
//        return ApiResponse.builder()
//                .success(true)
//                .message("Lấy danh sách mã xác thực thành công.")
//                .data(list)
//                .build();
//    }
//
//    // ✅ Lấy mã xác thực theo ID
//    @GetMapping("/{id}")
//    public ApiResponse<Object> getById(@PathVariable int id) {
//        VerificationResponseDto dto = verificationService.findById(id);
//        return ApiResponse.builder()
//                .success(true)
//                .message("Lấy mã xác thực thành công.")
//                .data(dto)
//                .build();
//    }
//
//    // ✅ Tạo mới mã xác thực
//    @PostMapping
//    public ApiResponse<Object> create(@RequestBody CreationVerificationDto dto) {
//        boolean result = verificationService.insert(dto);
//        return ApiResponse.builder()
//                .success(result)
//                .message(result ? "Tạo mã xác thực thành công." : "Tạo thất bại.")
//                .data(null)
//                .build();
//    }
//
//    // ✅ Cập nhật mã xác thực
//    @PutMapping
//    public ApiResponse<Object> update(@RequestBody CreationVerificationDto dto) {
//        boolean result = verificationService.update(dto);
//        return ApiResponse.builder()
//                .success(result)
//                .message(result ? "Cập nhật thành công." : "Cập nhật thất bại.")
//                .data(null)
//                .build();
//    }
//
//    // ✅ Xóa mã xác thực theo ID
//    @DeleteMapping("/{id}")
//    public ApiResponse<Object> delete(@PathVariable int id) {
//        boolean result = verificationService.deleteById(id);
//        return ApiResponse.builder()
//                .success(result)
//                .message(result ? "Xóa thành công." : "Xóa thất bại.")
//                .data(null)
//                .build();
//    }
//}
