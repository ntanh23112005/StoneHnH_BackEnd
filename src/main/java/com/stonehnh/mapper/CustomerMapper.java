package com.stonehnh.mapper;

import com.stonehnh.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerMapper {

    /**
     * Lấy danh sách tất cả customers trong bảng customers
     *
     * @return Danh sách thông tin của tất cả customers
     */
    @Select("SELECT * FROM customers")
    List<Customer> findAllCustomers();


}
