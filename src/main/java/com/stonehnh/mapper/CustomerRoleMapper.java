package com.stonehnh.mapper;

import com.stonehnh.entity.CustomerRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerRoleMapper {

    /**
     * Lấy danh sách tất cả vai trò customers trong bảng customer_roles
     *
     * @return Danh sách thông tin của tất cả customers + vai trò
     */
    @Select("SELECT id, customer_id, role_id FROM customer_roles")
    List<CustomerRole> findAllCustomerRoles();

    /**
     * Thêm vai trò một customer mới
     *
     * @return Số dòng bị ảnh hưởng
     */
    @Insert("INSERT INTO customer_roles (id, customer_id, role_id)" +
            "VALUES ( #{customerRole.id}, #{customerRole.customerId}, #{customerRole.roleId})")
    int insertCustomerRole(@Param("customerRole") CustomerRole customerRole);

    /**
     * Cập nhật vai trò cho customer
     *
     * @return Số dòng bị ảnh hưởng
     */
    @Update("UPDATE customer_roles SET id = #{customerRole.id}, " +
            "customer_id = #{customerRole.customerId}, role_id = #{customerRole.roleId}" +
            "WHERE customer_id = #{customerRole.customerId}")
    int updateCustomerRole(@Param("customerRole") CustomerRole customerRole);

    /**
     * Xoá customer theo ID
     *
     * @return Số dòng bị ảnh hưởng
     */
    @Delete("DELETE FROM customer_roles WHERE customer_id = #{customerId}")
    int deleteCustomerById(@Param("customerId") String customerId);

    /**
     * Lấy vai trò customer theo Customer ID
     *
     * @param customerId Mã customer cần tìm
     * @return Thông tin customer nếu tồn tại, ngược lại trả về null
     */
    @Select("SELECT id, customer_id, role_id FROM customer_roles" +
            "WHERE customer_id = #{customerId}")
    CustomerRole findCustomerRoleByCustomerId(@Param("customerId") String customerId);
}
