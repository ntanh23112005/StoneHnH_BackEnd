package com.stonehnh.customer.service;

import com.stonehnh.customer.dto.request.CreationCustomerRoleDto;
import com.stonehnh.customer.dto.response.CustomerRoleResponseDto;
import com.stonehnh.customer.entity.CustomerRole;

import java.util.List;

public interface CustomerRoleService {

    /**
     * Lấy danh sách tất cả customer roles
     * @return Danh sách customer roles
     */
    List<CustomerRoleResponseDto> getAllCustomerRoles();

    /**
     * Tạo mới 1 Customer role
     * @return Số dòng bị ảnh hưởng
     */
    int createNewCustomerRole(CreationCustomerRoleDto customerRoleDto);

    /**
     * Cập nhật 1 Customer role
     * @return Số dòng bị ảnh hưởng
     */
    int updateCustomerRole(String customerId, CustomerRole customerRole);

    /**
     * Xóa 1 Customer role
     * @return Số dòng bị ảnh hưởng
     */
    int deleteCustomerRole(String customerId);

    /**
     * Tìm vai trò 1 customer theo Customer Id
     * @return 1 customer + vai trò theo id đã tìm
     */
    CustomerRoleResponseDto findCustomerRoleByCustomerId (String customerId);
}
