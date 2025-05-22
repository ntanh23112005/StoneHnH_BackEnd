package com.stonehnh.converter;

import com.stonehnh.dto.request.CreationCustomerRoleDto;
import com.stonehnh.dto.response.CustomerRoleResponseDto;
import com.stonehnh.entity.CustomerRole;

import java.util.ArrayList;
import java.util.List;

public class CustomerRoleConverter {

    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return List customer role đã được convert từ entity -> dto
     */
    public static List<CustomerRoleResponseDto> toDtoList(List<CustomerRole> customerRoles) {
        List<CustomerRoleResponseDto> dtoList = new ArrayList<>();

        for (CustomerRole c : customerRoles) {
            CustomerRoleResponseDto dto = new CustomerRoleResponseDto();
            dto.setCustomerId(c.getCustomerId());
            dto.setRoleId(c.getRoleId());
            dtoList.add(dto);
        }

        return dtoList;
    }

    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return 1 customer role đã được convert từ entity -> dto
     */
    public static CustomerRoleResponseDto toDto(CustomerRole customerRole){
        return CustomerRoleResponseDto.builder()
                .customerId(customerRole.getCustomerId())
                .roleId(customerRole.getRoleId())
                .build();
    }

    /**
     * Hàm convert data từ frontend xuống entity backend để làm việc với database
     *
     * @return List customer role đã được convert từ dto -> entity
     */
    public static CustomerRole toEntity(CreationCustomerRoleDto customerRoleDto) {
        CustomerRole customerRole = new CustomerRole();
        customerRole.setCustomerId(customerRoleDto.getCustomerId());
        customerRole.setRoleId(customerRoleDto.getRoleId());
        return customerRole;
    }
}
