package com.stonehnh.customer.service.impl;

import com.stonehnh.customer.converter.CustomerRoleConverter;
import com.stonehnh.customer.service.CustomerRoleService;
import com.stonehnh.customer.dto.request.CreationCustomerRoleDto;
import com.stonehnh.customer.dto.response.CustomerRoleResponseDto;
import com.stonehnh.customer.entity.CustomerRole;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.customer.mapper.CustomerRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerRoleServiceImpl implements CustomerRoleService {

    private final CustomerRoleMapper customerRoleMapper;

    public CustomerRoleServiceImpl(CustomerRoleMapper customerRoleMapper){this.customerRoleMapper = customerRoleMapper;}

    @Override
    public List<CustomerRoleResponseDto> getAllCustomerRoles() {

        List<CustomerRole> customerRoleList = customerRoleMapper.findAllCustomerRoles();

        List<CustomerRoleResponseDto> customerRoleResponseDtos = CustomerRoleConverter.toDtoList(customerRoleList);

        return customerRoleResponseDtos;
    }

    @Override
    public int createNewCustomerRole(CreationCustomerRoleDto customerRoleDto) {
        /*
         * TODO: Xử lý logic riêng (nếu có)
         *
         * TODO: Convert from dto to entity to insert database
         *
         * */
        CustomerRole customerRole = CustomerRoleConverter.toEntity(customerRoleDto);
        return customerRoleMapper.insertCustomerRole(customerRole);
    }

    @Override
    public int updateCustomerRole(String customerId, CustomerRole customerRole) {
        boolean isExistedCustomer = customerRoleMapper.isExistedCustomerById(customerId);
        if (!isExistedCustomer){
            // TODO: Xử lý theo nghiệp vụ
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        return customerRoleMapper.updateCustomerRole(customerRole);
    }

    @Override
    public int deleteCustomerRole(String customerId) {
        // TODO: Xử lý theo nghiệp vụ
        boolean isExistedCustomer = customerRoleMapper.isExistedCustomerById(customerId);
        if(!isExistedCustomer){
            // TODO: Xử lý theo nghiệp vụ
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        return customerRoleMapper.deleteCustomerById(customerId);
    }

    @Override
    public CustomerRoleResponseDto findCustomerRoleByCustomerId(String customerId) {
        CustomerRole customerRole = customerRoleMapper.findCustomerRoleByCustomerId(customerId);
        /*
        * TODO: Xử lý theo nghiệp vụ
        * */
        if (customerRole == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }

        return CustomerRoleConverter.toDto(customerRole);
    }
}
