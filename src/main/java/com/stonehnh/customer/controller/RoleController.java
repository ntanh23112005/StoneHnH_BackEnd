package com.stonehnh.customer.controller;

import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.customer.dto.request.CreationRoleDto;
import com.stonehnh.customer.dto.response.RoleResponseDto;
import com.stonehnh.customer.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController (RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Lấy danh sách tất cả các vai trò.
     * GET /api/v1/roles
     * @return ApiResponse chứa danh sách RoleResponseDto
     */
    @GetMapping
    public ApiResponse<List<RoleResponseDto>> getAllRoles() {
        List<RoleResponseDto> roles = roleService.getAllRoles();
        return ApiResponse.<List<RoleResponseDto>>builder()
                .success(true)
                .message("Lấy danh sách vai trò thành công.")
                .data(roles)
                .build();
    }

    /**
     * Tạo mới một vai trò.
     * POST /api/v1/roles
     * @param request CreationRoleDto chứa thông tin vai trò mới
     * @return ApiResponse chứa RoleResponseDto của vai trò đã tạo
     */
//    @PostMapping
//    public ApiResponse<Integer> createNewRole(@RequestBody CreationRoleDto request) {
//        int rowsAffected = roleService.createRole(request);
//        return ApiResponse.<Integer>builder()
//                .success(true)
//                .message("Tạo vai trò mới thành công.")
//                .data(rowsAffected) // You might return the created RoleResponseDto instead
//                .build();
    //   }

    /**
     * Cập nhật thông tin một vai trò.
     * PUT /api/v1/roles/{roleId}
     * @param roleId ID của vai trò cần cập nhật
     * @param request CreationRoleDto chứa thông tin cập nhật
     * @return ApiResponse với số dòng bị ảnh hưởng
     */
//    @PutMapping("/{roleId}")
//    public ApiResponse<Integer> updateRole(@PathVariable String roleId, @RequestBody CreationRoleDto request) {
//        int rowsAffected = roleService.updateRole(roleId, request);
//        if (rowsAffected > 0) {
//            return ApiResponse.<Integer>builder()
    //                   .success(true)
    //                   .message("Cập nhật vai trò thành công.")
    //                   .data(rowsAffected)
//                    .build();
    //       } else {
    //           return ApiResponse.<Integer>builder()
    //                   .success(false)
    //                   .message("Không tìm thấy vai trò để cập nhật hoặc không có thay đổi.")
    //                   .data(0)
    //                   .build();
    //       }
    //   }

    /**
     * Xóa một vai trò theo ID.
     * DELETE /api/v1/roles/{roleId}
     * @param roleId ID của vai trò cần xóa
     * @return ApiResponse với số dòng bị ảnh hưởng
     */
    //   @DeleteMapping("/{roleId}")
    //   public ApiResponse<Integer> deleteRole(@PathVariable String roleId) {
    //       int rowsAffected = roleService.deleteRole(roleId);
    //       if (rowsAffected > 0) {
    //           return ApiResponse.<Integer>builder()
    //                   .success(true)
    //                   .message("Xóa vai trò thành công.")
    //                   .data(rowsAffected)
    //                   .build();
    //       } else {
    //         return ApiResponse.<Integer>builder()
    //                   .success(false)
    //                   .message("Không tìm thấy vai trò để xóa.")
    //                   .data(0)
    //                   .build();
    //       }
    //   }

    /**
     * Lấy thông tin một vai trò theo ID.
     * GET /api/v1/roles/{roleId}
     * @param roleId ID của vai trò cần tìm
     * @return ApiResponse chứa RoleResponseDto
     */
    //   @GetMapping("/{roleId}")
    //   public ApiResponse<RoleResponseDto> getRoleById(@PathVariable String roleId) {
    //       try {
    //           RoleResponseDto role = roleService.findRoleById(roleId);
    //           return ApiResponse.<RoleResponseDto>builder()
    //                   .success(true)
    //                   .message("Lấy thông tin vai trò thành công.")
    //                   .data(role)
    //                   .build();
    //       } catch (NoSuchElementException e) {
    //           return ApiResponse.<RoleResponseDto>builder()
    //                   .success(false)
    //                   .message(e.getMessage())
    //                   .data(null)
    //                   .build();
    //       }
//    }
}