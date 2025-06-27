package com.stonehnh.customer.service.impl;

import com.stonehnh.customer.converter.CustomerConverter;
import com.stonehnh.customer.converter.CustomerRoleConverter;
import com.stonehnh.customer.dto.request.CreationCustomerRoleDto;
import com.stonehnh.customer.entity.CustomerRole;
import com.stonehnh.customer.mapper.CustomerRoleMapper;
import com.stonehnh.customer.service.CustomerService;
import com.stonehnh.customer.dto.request.CreationCustomerDto;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.entity.Customer;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.customer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final CustomerMapper customerMapper;
    private final CustomerRoleMapper customerRoleMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRoleMapper customerRoleMapper){
        this.customerMapper = customerMapper;
        this.customerRoleMapper = customerRoleMapper;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {

        List<Customer> customerList = customerMapper.findAllCustomers();

        List<CustomerResponseDto> customerResponseDtos = CustomerConverter.toDtoList(customerList);

        return customerResponseDtos;
    }

    @Override
    @Transactional
    public CustomerResponseDto createNewCustomerWithRole(CreationCustomerDto dto, List<String> roleIds) {
        // Tạo user
        String newId = UUID.randomUUID().toString();
        Customer customer = CustomerConverter.toEntity(dto);
        customer.setCustomerId(newId);
        //mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        customer.setPassword(encodedPassword);

        customerMapper.insertCustomer(customer);

        // Gán role
        for (String roleId : roleIds) {
            CreationCustomerRoleDto roleDto = new CreationCustomerRoleDto();
            roleDto.setCustomerId(newId);
            roleDto.setRoleId(roleId);
            customerRoleMapper.insertCustomerRole(CustomerRoleConverter.toEntity(roleDto));
        }

        // Convert sang DTO
        return CustomerConverter.toDto(customer, roleIds);
    }


    @Override
    @Transactional
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
    public int deleteCustomerId(String customerId) {
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

    @Override
    public CustomerResponseDto findCustomerByEmail(String email) {
        CustomerResponseDto customer = customerMapper.findCustomerByEmail(email);
        /*
         * TODO: Xử lý logic nếu cần
         * */
        if(customer == null){
            System.out.println("Lỗi: " + ErrorCode.CUSTOMER_NOT_FOUND);
            return null;
        }
        return customer;
    }

    @Override
    public boolean checkLoginByEmailAndPassword(String email, String password) {
        CustomerResponseDto customer = customerMapper.findCustomerByEmail(email);
        if(customer == null){
            return false;
        }
        return passwordEncoder.matches(password, customer.getPassword());
    }

    @Override
    public List<String> getRolesByEmail(String email) {
        return customerMapper.getRolesByEmail(email);
    }
}
