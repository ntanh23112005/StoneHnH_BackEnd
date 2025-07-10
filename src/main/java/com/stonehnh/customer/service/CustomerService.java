package com.stonehnh.customer.service;

import com.stonehnh.customer.dto.request.CreationCustomerDto;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {

    /**
     * Lấy danh sách tất cả customers
     * @return Danh sách customers
     */
    List<CustomerResponseDto> getAllCustomers();

    /**
     * Tạo mới 1 Customer + role
     * @param creationCustomerDto dto tạo mới
     * @param roleId role muốn truyền
     * @return dto CustomerResponse mới thêm
     */
    CustomerResponseDto createNewCustomerWithRole(CreationCustomerDto creationCustomerDto, List<String> roleId);

    /**
     * Cập nhật Customer và customer_role
     * @param dto thuộc tính của customer
     * @param roleIds list quyền
     * @return CustomerResponseDto
     */
    CustomerResponseDto updateCustomerWithRole(CreationCustomerDto dto, List<String> roleIds);

    /**
     * Cập nhật 1 Customer
     * @param customerId Customer Id
     * @param customer 1 đối tượng Customer
     * @return Số dòng bị ảnh hưởng
     */
    int updateCustomer(String customerId, CreationCustomerDto customer);

    /**
     * Xóa 1 Customer
     * @param customerId Customer Id
     * @return Số dòng bị ảnh hưởng
     */
    int deleteCustomerId(String customerId);

    /**
     * Tìm 1 customer theo Customer Id
     * @param customerId Customer Id
     * @return 1 customer theo id đã tìm
     */
    CustomerResponseDto findCustomerByCustomerId (String customerId);

    /**
     * Tìm 1 customer theo email
     * @param email Customer's email
     * @return 1 customer theo email
     */
    CustomerResponseDto findCustomerByEmail (String email);

    /**
     * Tìm 1 customer theo email
     * @param email Customer's email
     * @return 1 customer theo email
     */
    boolean checkLoginByEmailAndPassword (String email, String password);

    /**
     * Lấy role theo email
     * @param email Customer's email
     * @return Role (hoặc List nếu nhiều) theo email
     */
    List<String> getRolesByEmail(String email);

    /**
     * Gửi mã xác thực về email và trả về mã
     * @param email địa chỉ email
     * @return mã xác thực 6 chữ số
     */
    String sendVerificationCodeToEmail(String email);

    /**
     * Đặt lại mật khẩu mới cho email đã xác thực
     * @param email Email của khách hàng
     * @param newPassword Mật khẩu mới (chưa mã hóa)
     */
    void resetPassword(String email, String newPassword);

    /**
     * Cập nhật tên file hình theo id
     * @param customerId id của khách hàng
     * @param file file hình cần lưu
     * @return Customer đã hoàn thành
     */
    String uploadAvatar(String customerId, MultipartFile file);

    /**
     * Đếm số lượng customer
     */
    int countAllCustomers();
}
