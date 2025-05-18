package com.stonehnh.converter;

import com.stonehnh.dto.request.CreationCustomerDto;
import com.stonehnh.dto.response.CustomerResponseDto;
import com.stonehnh.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerConverter {

    // hàm đổi từ db lên thành dto để lên frontend
    public static List<CustomerResponseDto> toDtoList(List<Customer> customers) {
        List<CustomerResponseDto> dtoList = new ArrayList<>();

        for (Customer c : customers) {
            CustomerResponseDto dto = new CustomerResponseDto();
            dto.setCustomerId(c.getCustomerId());
            dto.setCustomerName(c.getCustomerName());
            dto.setEmail(c.getEmail());
            dto.setPhoneNumber(c.getPhoneNumber());
            dto.setCustomerAddress(c.getCustomerAddress());
            dto.setCreatedDate(c.getCreatedDate());
            dto.setCustomerPicture(c.getCustomerPicture());
            dto.setVerifyStatus(c.getVerifyStatus());
            dto.setAccountStatus(c.getAccountStatus());

            dtoList.add(dto);
        }

        return dtoList;
    }

    //hàm đổi từ frontend từ dto thành entity để đua xuống backend
    public static Customer toEntity(CreationCustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setPassword(customerDto.getPassword());
        customer.setCustomerAddress(customerDto.getCustomerAddress());
        customer.setCreatedDate(customerDto.getCreatedDate());
        customer.setCustomerPicture(customerDto.getCustomerPicture());
        customer.setVerifyStatus(customerDto.getVerifyStatus());
        customer.setAccountStatus(customerDto.getAccountStatus());

        return customer;
    }
}
