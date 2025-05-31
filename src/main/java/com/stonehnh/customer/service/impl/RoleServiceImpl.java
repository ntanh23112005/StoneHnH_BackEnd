package com.stonehnh.customer.service.impl;

import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.customer.converter.RoleConverter;
import com.stonehnh.customer.dto.request.CreationRoleDto;
import com.stonehnh.customer.dto.response.RoleResponseDto;
import com.stonehnh.customer.entity.Roles;
import com.stonehnh.customer.mapper.RoleMapper;
import com.stonehnh.customer.service.RoleService;

import java.util.List;
import java.util.UUID;

public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        List<Roles> roles = roleMapper.findAllRoles();
        return RoleConverter.toDtoList(roles);
    }

//    @Override
//    public int createNewRole(CreationRoleDto creationRoleDto) {
//        // Tạo mới Role entity
//        Roles role = RoleConverter.toEntity(creationRoleDto);
//        // Sinh ID mới cho role (nếu cần)
//        role.setRoleId(UUID.randomUUID().toString());
//        return roleMapper.insertRole(role);
//    }
//
//    @Override
//    public int updateRole(String roleId, CreationRoleDto dto) {
//        // Kiểm tra role có tồn tại không
//        boolean isExisted = roleMapper.isExistedRoleById(roleId);
//        if (!isExisted) {
//            throw new AppException(ErrorCode.ROLE_NOT_FOUND);
//        }
//
//        Roles roleToUpdate = RoleConverter.toEntity(dto);
//        roleToUpdate.setRoleId(roleId); // đảm bảo đúng id
//
//        return roleMapper.updateRole(roleToUpdate);
//    }
//
//    @Override
//    public int deleteRole(String roleId) {
//        boolean isExisted = roleMapper.isExistedRoleById(roleId);
//        if (!isExisted) {
//            throw new AppException(ErrorCode.ROLE_NOT_FOUND);
//        }
//        return roleMapper.deleteRoleById(roleId);
//    }
//
//    @Override
//    public RoleResponseDto findRoleById(String roleId) {
//        Roles role = roleMapper.findRoleById(roleId);
//        if (role == null) {
//            throw new AppException(ErrorCode.ROLE_NOT_FOUND);
//        }
//        return RoleConverter.toDto(role);
//    }
}
