package com.stonehnh.customer.converter;

import com.stonehnh.customer.dto.request.CreationRoleDto;
import com.stonehnh.customer.dto.response.RoleResponseDto;
import com.stonehnh.customer.entity.Roles;

import java.util.ArrayList;
import java.util.List;

public class RoleConverter {
    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return List role role đã được convert từ entity -> dto
     */
    public static List<RoleResponseDto> toDtoList(List<Roles> roles) {
        List<RoleResponseDto> dtoList = new ArrayList<>();

        for (Roles r : roles) {
            RoleResponseDto dto = new RoleResponseDto();
            dto.setRoleId(r.getRoleId());
            dto.setRoleName(r.getRoleName());
            dtoList.add(dto);
        }

        return dtoList;
    }
    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return 1  role đã được convert từ entity -> dto
     */
    public static RoleResponseDto toDto(Roles role) {
        if (role == null) return null;

        return RoleResponseDto.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .build();
    }

    /**
     * Hàm convert data từ frontend xuống entity backend để làm việc với database
     *
     * @return List  role đã được convert từ dto -> entity
     */
    public static Roles toEntity(CreationRoleDto dto) {
        if (dto == null) return null;

        Roles role = new Roles();
        role.setRoleId(dto.getRoleId());
        role.setRoleName(dto.getRoleName());
        return role;
    }
    }


