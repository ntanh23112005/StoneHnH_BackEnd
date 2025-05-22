package com.stonehnh.service.impl;

import com.stonehnh.converter.CustomerRoleConverter;
import com.stonehnh.dto.request.CreationCustomerRoleDto;
import com.stonehnh.dto.response.CustomerRoleResponseDto;
import com.stonehnh.entity.CustomerRole;
import com.stonehnh.mapper.CustomerRoleMapper;
import com.stonehnh.service.CustomerRoleService;
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
        return 0;
    }

    @Override
    public int updateCustomerRole(String customerId, CustomerRole customerRole) {
        return 0;
    }

    @Override
    public int deleteCustomerRole(String customerId) {
        return 0;
    }

    @Override
    public CustomerRoleResponseDto findCustomerRoleByCustomerId(String customerId) {
        return null;
    }
}
