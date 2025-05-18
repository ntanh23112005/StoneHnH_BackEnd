package com.stonehnh.service;

import com.stonehnh.dto.response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    /**
     * Lấy danh sách tất cả customers
     * @return Danh sách customers
     */
    public List<CustomerResponseDto> getAllCustomers();
}
