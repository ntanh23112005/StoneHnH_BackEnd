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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final CustomerMapper customerMapper;
    private final CustomerRoleMapper customerRoleMapper;
    private final JavaMailSender mailSender;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRoleMapper customerRoleMapper, JavaMailSender mailSender){
        this.customerMapper = customerMapper;
        this.customerRoleMapper = customerRoleMapper;
        this.mailSender = mailSender;
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
        // Tạo customer entity
        Customer customer = CustomerConverter.toEntity(dto);
        customer.setCustomerId(UUID.randomUUID().toString());

        // Gán mặc định nếu null
        if (customer.getPhoneNumber() == null) {
            customer.setPhoneNumber("");
        }
        if (customer.getCustomerAddress() == null) {
            customer.setCustomerAddress("");
        }
        if (customer.getCreatedDate() == null) {
            customer.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));
        }
        if (customer.getVerifyStatus() == null) {
            customer.setVerifyStatus(true);
        }
        if (customer.getAccountStatus() == null) {
            customer.setAccountStatus(true);
        }
        if (customer.getCustomerPicture() == null) {
            customer.setCustomerPicture(null);
        }

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        customer.setPassword(encodedPassword);

        // Insert customer
        customerMapper.insertCustomer(customer);

        // Nếu roleIds null hoặc empty => mặc định quyền user
        if (roleIds == null || roleIds.isEmpty()) {
            roleIds = List.of("R01");
        }

        // Gán quyền
        for (String roleId : roleIds) {
            CreationCustomerRoleDto roleDto = new CreationCustomerRoleDto();
            roleDto.setCustomerId(customer.getCustomerId());
            roleDto.setRoleId(roleId);
            customerRoleMapper.insertCustomerRole(CustomerRoleConverter.toEntity(roleDto));
        }

        // Convert trả về DTO
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

    /**
     * Gửi email chứa mã xác thực 6 chữ số
     * @param toEmail email nhận
     * @return mã xác thực đã gửi
     */
    public String sendVerificationCodeToEmail(String toEmail) {
        // Tạo mã 6 chữ số
        String code = generateSixDigitCode();

        // Tạo email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("supporrtstonehnh@gmail.com"); // Thay bằng email gửi
        message.setTo(toEmail);
        message.setSubject("Mã xác thực của bạn");
        message.setText("Mã xác thực của bạn là: " + code);

        // Gửi
        mailSender.send(message);

        return code;
    }

    private String generateSixDigitCode() {
        SecureRandom random = new SecureRandom();
        int number = random.nextInt(900000) + 100000; // 100000 - 999999
        return String.valueOf(number);
    }

    @Override
    @Transactional
    public void resetPassword(String email, String newPassword) {
        Customer customer = customerMapper.findCustomerByCustomerId(
                customerMapper.findCustomerByEmail(email).getCustomerId()
        );
        if (customer == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(newPassword);

        // Gọi mapper riêng để chỉ update password
        int affectedRows = customerMapper.updatePasswordByEmail(email, encodedPassword);
        if (affectedRows == 0) {
            System.out.println("Lỗi");
        }
    }
}
