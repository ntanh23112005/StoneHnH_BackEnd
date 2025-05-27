package com.stonehnh.converter;

import com.stonehnh.dto.request.CreationCustomerRoleDto;
import com.stonehnh.dto.response.CustomerRoleResponseDto;
import com.stonehnh.entity.CustomerRole;

import java.util.ArrayList;
import java.util.List;

public class RoleConverter {
    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return List Role đã được convert từ entity -> dto
     */
    public  static List<CustomerRoleResponseDto> toDtoList(List<CustomerRole> roles){
        List<CustomerRoleResponseDto> dtoList= new ArrayList<>();
        for(CustomerRole r : roles){
            CustomerRoleResponseDto dto = new CustomerRoleResponseDto();
            dto.setRoleId(r.getRoleId());
            dto.setCustomerId(r.getCustomerId());
            dtoList.add(dto);
        }
        return dtoList;
    }
    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return 1 Role đã được convert từ entity -> dto
     */
    public static  CustomerRoleResponseDto toDto(CustomerRole r){
        return CustomerRoleResponseDto.builder()
                .roleId(r.getRoleId())
                .customerId(r.getCustomerId())
                .build();
    }
    /**
     * Hàm convert data từ frontend xuống entity backend để làm việc với database
     *
     * @return List Role đã được convert từ dto -> entity
     */
    public static  CustomerRole toEntity(CreationCustomerRoleDto dto){
        CustomerRole r = new CustomerRole();
        r.setRoleId(dto.getRoleId());
        r.setCustomerId(dto.getCustomerId());
        return  r;
    }
}
