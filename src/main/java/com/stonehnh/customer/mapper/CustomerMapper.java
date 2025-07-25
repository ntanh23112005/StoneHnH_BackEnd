package com.stonehnh.customer.mapper;

import com.stonehnh.customer.dto.request.CreationCustomerDto;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.entity.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface CustomerMapper {

    /**
     * Lấy danh sách tất cả customers trong bảng customers
     *
     * @return Danh sách thông tin của tất cả customers
     */
    @Select("SELECT customer_id, customer_name, email," +
            "phone_number, password, customer_address," +
            "created_date, customer_picture, verify_status, account_status " +
            " FROM customers")
    List<Customer> findAllCustomers();

    /**
     * Thêm một customer mới
     *
     * @return Số dòng bị ảnh hưởng
     */
    @Insert("INSERT INTO customers (customer_id, customer_name, email, phone_number, password, customer_address, " +
            "created_date, customer_picture, verify_status, account_status) " +
            "VALUES (#{customer.customerId}, #{customer.customerName}, #{customer.email}, #{customer.phoneNumber}, #{customer.password}, " +
            "#{customer.customerAddress}, #{customer.createdDate}, #{customer.customerPicture}, " +
            "#{customer.verifyStatus}, #{customer.accountStatus})")
    int insertCustomer(@Param("customer") Customer customer);

    /**
     * Cập nhật thông tin customer
     * @param customer 1 customer đã có sẵn
     * @return Số dòng bị ảnh hưởng
     */
    @Update("UPDATE customers SET customer_name = #{customer.customerName}, email = #{customer.email}, " +
            "phone_number = #{customer.phoneNumber}, password = #{customer.password}, " +
            "customer_address = #{customer.customerAddress}, customer_picture = #{customer.customerPicture}, " +
            "verify_status = #{customer.verifyStatus}, account_status = #{customer.accountStatus} " +
            "WHERE customer_id = #{customer.customerId}")
    int updateCustomer(@Param("customer") Customer customer);

    /**
     * Xoá customer theo ID
     * @param customerId mã id của customer cần xóa
     * @return Số dòng bị ảnh hưởng
     */
    @Delete("DELETE FROM customers WHERE customer_id = #{customerId}")
    int deleteCustomerById(@Param("customerId") String customerId);

    /**
     * Lấy thông tin customer theo ID
     * @param customerId Mã customer cần tìm
     * @return Thông tin customer nếu tồn tại, ngược lại trả về null
     */
    @Select("SELECT customer_id, customer_name, email, phone_number, password, customer_address, " +
            "created_date, customer_picture, verify_status, account_status " +
            "FROM customers WHERE customer_id = #{customerId}")
    Customer findCustomerByCustomerId(@Param("customerId") String customerId);

    /**
     * Kiểm tra sự tồn tại của customer theo ID
     * @param customerId Mã customer cần kiểm tra
     * @return true nếu tồn tại, false nếu không
     */
    @Select("SELECT COUNT(c.customer_id) > 0 FROM customers c WHERE c.customer_id = #{customerId}")
    boolean isExistedCustomerById(@Param("customerId") String customerId);

    @Select("SELECT COUNT(c.customer_id) FROM customers c WHERE c.email = #{email} AND c.password = #{password}")
    boolean checkLoginByEmailAndPassWord(@Param("email") String email, @Param("password") String password);

    CustomerResponseDto findCustomerByEmail(@Param("email") String email);

    List<String> getRolesByEmail(@Param("email") String email);

    List<CustomerResponseDto> findCustomersByEmailLike(@Param("email") String email);

    /**
     * Cập nhật mật khẩu theo email
     * @param email email của khách hàng
     * @param encodedPassword mật khẩu đã mã hóa
     * @return số dòng bị ảnh hưởng
     */
    @Update("UPDATE customers SET password = #{encodedPassword} WHERE email = #{email}")
    int updatePasswordByEmail(@Param("email") String email, @Param("encodedPassword") String encodedPassword);

    /**
     * Kiểm tra sự tồn tại của email
     * @param email Email cần kiểm tra
     * @return true nếu tồn tại
     */
    @Select("SELECT COUNT(1) > 0 FROM customers WHERE email = #{email}")
    boolean existsByEmail(@Param("email") String email);

    /**
     * Kiểm tra sự tồn tại của số điện thoại
     * @param phoneNumber Số điện thoại cần kiểm tra
     * @return true nếu tồn tại
     */
    @Select("SELECT COUNT(1) > 0 FROM customers WHERE phone_number = #{phoneNumber}")
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    /**
     * Cập nhật tên file hình theo id
     * @param customerId id của khách hàng
     * @param customerPicture tên file hình cần lưu
     * @return Customer đã hoàn thành
     */
    @Update("UPDATE customers c" +
            "SET c.customer_picture = #{customerPicture} " +
            "WHERE c.customer_id = #{customerId}")
    Customer uploadImage(@Param("customerId") String customerId ,@Param("customerPicture") String customerPicture);

    /**
     * Đếm số lượng customer
     * */
    @Select("SELECT COUNT(*) FROM customers")
    int countCustomers();

    /**
     * Lấy danh sách Customer + List<Role> của customer
     * @return List<CustomerResponseDto>
     */
    List<CustomerResponseDto> getAllCustomerAndRoles();
}
