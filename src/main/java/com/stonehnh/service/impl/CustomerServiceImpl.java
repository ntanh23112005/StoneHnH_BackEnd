package com.stonehnh.service.impl;

import com.stonehnh.converter.CustomerConverter;
import com.stonehnh.dto.request.CreationCustomerDto;
import com.stonehnh.dto.response.CustomerResponseDto;
import com.stonehnh.entity.Customer;
import com.stonehnh.enums.ErrorCode;
import com.stonehnh.exception.AppException;
import com.stonehnh.mapper.CustomerMapper;
import com.stonehnh.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper){
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {

        List<Customer> customerList = customerMapper.findAllCustomers();

        List<CustomerResponseDto> customerResponseDtos = CustomerConverter.toDtoList(customerList);

        return customerResponseDtos;
    }

    @Override
    public int createNewCustomer(CreationCustomerDto creationCustomerDto) {
        /*
        * TODO: Xử lý logic riêng (nếu có)
        *
        * TODO: Convert from dto to entity to insert database
        *
        * */

        Customer customer = CustomerConverter.toEntity(creationCustomerDto);
        customer.setCustomerId(String.valueOf(UUID.randomUUID()));
        return customerMapper.insertCustomer(customer);
    }

    @Override
    public int updateCustomer(String id, Customer customer) {
        /*
        * TODO: Xử lý logic nếu có
        * */

        boolean isExistedCustomer = customerMapper.isExistedCustomerById(id);
        if(!isExistedCustomer){
            // TODO: Xử lý theo nghiệp vụ
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }

        return customerMapper.updateCustomer(customer);

    }

    @Override
    public int deleteCustomer(String customerId) {
        /*
         * TODO: Xử lý logic nếu có
         * */

        boolean isExistedCustomer = customerMapper.isExistedCustomerById(customerId);
        if(!isExistedCustomer){
            // TODO: Xử lý theo nghiệp vụ
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }

        return customerMapper.deleteCustomerById(customerId);
    }

    @Override
    public CustomerResponseDto findCustomerByCustomerId(String customerId) {
        Customer customer = customerMapper.findCustomerByCustomerId(customerId);

        /*
        * TODO: Xử lý logic nếu cần
        * */

        if(customer == null){
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }

        return CustomerConverter.toDto(customer);
    }
}
