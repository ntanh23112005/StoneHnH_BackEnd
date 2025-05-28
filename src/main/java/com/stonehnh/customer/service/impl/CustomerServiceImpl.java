package com.stonehnh.customer.service.impl;

import com.stonehnh.customer.converter.CustomerConverter;
import com.stonehnh.customer.service.CustomerService;
import com.stonehnh.customer.dto.request.CreationCustomerDto;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.entity.Customer;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.customer.mapper.CustomerMapper;
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
