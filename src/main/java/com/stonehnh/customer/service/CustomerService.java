package com.stonehnh.customer.service;

import com.stonehnh.customer.dto.request.CreationCustomerDto;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.entity.Customer;

import java.util.List;

public interface CustomerService {

    /**
     * Lấy danh sách tất cả customers
     * @return Danh sách customers
     */
    List<CustomerResponseDto> getAllCustomers();

    /**
     * Tạo mới 1 Customer
     * @return Số dòng bị ảnh hưởng
     */
    int createNewCustomer(CreationCustomerDto creationCustomerDto);

    /**
     * Cập nhật 1 Customer
     * @return Số dòng bị ảnh hưởng
     */
    int updateCustomer(String customerId, Customer customer);

    /**
     * Xóa 1 Customer
     * @return Số dòng bị ảnh hưởng
     */
    int deleteCustomer(String customerId);

    /**
     * Tìm 1 customer theo Customer Id
     * @return 1 customer theo id đã tìm
     */
    CustomerResponseDto findCustomerByCustomerId (String customerId);
}
