package com.stonehnh.service.impl;

import com.stonehnh.converter.CustomerConverter;
import com.stonehnh.dto.response.CustomerResponseDto;
import com.stonehnh.entity.Customer;
import com.stonehnh.mapper.CustomerMapper;
import com.stonehnh.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDto> getAllCustomers() {

        List<Customer> customerList = customerMapper.findAllCustomers();

        List<CustomerResponseDto> customerResponseDtos = CustomerConverter.toDtoList(customerList);

        return customerResponseDtos;
    }
}
