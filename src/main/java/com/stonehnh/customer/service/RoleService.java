package com.stonehnh.customer.service;

import com.stonehnh.customer.dto.request.CreationRoleDto;
import com.stonehnh.customer.dto.response.RoleResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    /**
     * Lấy danh sách tất cả roles
     * @return Danh sách roles
     */
    List<RoleResponseDto> getAllRoles();

    /**
     * Tạo mới 1 Role
     * @param creationRoleDto DTO chứa thông tin role mới
     * @return Số dòng bị ảnh hưởng
     */
//    int createNewRole(CreationRoleDto creationRoleDto);

    /**
     * Cập nhật 1 Role
     * @param roleId ID của role cần cập nhật
     * @param dto DTO chứa thông tin role cập nhật
     * @return Số dòng bị ảnh hưởng
     */
//    int updateRole(String roleId, CreationRoleDto dto);

    /**
     * Xóa 1 Role
     * @param roleId ID của role cần xóa
     * @return Số dòng bị ảnh hưởng
     */
//    int deleteRole(String roleId);

    /**
     * Tìm 1 role theo Role Id
     * @param roleId ID cần tìm
     * @return RoleResponse tương ứng
     */
//    RoleResponseDto findRoleById(String roleId);
}
