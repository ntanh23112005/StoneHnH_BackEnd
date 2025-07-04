package com.stonehnh.owner.controller;

import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.owner.dto.request.CreationOwnerDto;
import com.stonehnh.owner.dto.response.OwnerResponseDto;
import com.stonehnh.owner.service.OwnerService;
import org.springframework.web.bind.annotation.*;

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
}
