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
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        // Kiểm tra email tồn tại
        if (customerMapper.existsByEmail(dto.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

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
    public CustomerResponseDto updateCustomerWithRole(CreationCustomerDto dto, List<String> roleIds) {
        // Kiểm tra tồn tại
        CustomerResponseDto existing =  customerMapper.findCustomerByEmail(dto.getEmail());
        if (existing == null) {
            throw new RuntimeException("Người dùng không tồn tại");
        }

        // Cập nhật thông tin (trừ password nếu không gửi lại)
        existing.setCustomerName(dto.getCustomerName());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setCustomerAddress(dto.getCustomerAddress());
        existing.setVerifyStatus(dto.getVerifyStatus());
        existing.setAccountStatus(dto.getAccountStatus());
        if (dto.getCustomerPicture() != null) {
            existing.setCustomerPicture(dto.getCustomerPicture());
        }
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        customerMapper.updateCustomer(CustomerConverter.toEntity(existing)); // update trong DB

        // --- Xử lý roles ---
        if (roleIds != null && !roleIds.isEmpty()) {
            // Xóa role cũ
            customerRoleMapper.deleteCustomerById(existing.getCustomerId());

            // Gán lại role mới (nếu có)
            for (String roleId : roleIds) {
                CreationCustomerRoleDto roleDto = new CreationCustomerRoleDto();
                roleDto.setCustomerId(existing.getCustomerId());
                roleDto.setRoleId(roleId);
                customerRoleMapper.insertCustomerRole(CustomerRoleConverter.toEntity(roleDto));
            }
        }

        return existing;
    }

    @Override
    @Transactional
    public int updateCustomer(String id, CreationCustomerDto customer) {
        /*
         * TODO: Xử lý logic nếu có
         * */

        boolean isExistedCustomer = customerMapper.isExistedCustomerById(id);
        if(!isExistedCustomer){
            // TODO: Xử lý theo nghiệp vụ
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }

        return customerMapper.updateCustomer(CustomerConverter.toEntity(customer));

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
        // Tìm customer theo email
        Customer customer = customerMapper.findCustomerByCustomerId(
                customerMapper.findCustomerByEmail(email).getCustomerId()
        );

        if (customer == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }

        // So sánh password hiện tại với GOOGLE_PASSWORD
        boolean isGooglePassword = passwordEncoder.matches("GOOGLE_PASSWORD", customer.getPassword());
        System.out.println(isGooglePassword);
        if (isGooglePassword) {
            throw new AppException();
        }

        // Mã hóa mật khẩu mới
        String encodedPassword = passwordEncoder.encode(newPassword);

        // Update password
        int affectedRows = customerMapper.updatePasswordByEmail(email, encodedPassword);
        if (affectedRows == 0) {
            System.out.println("Lỗi khi update password");
        }
    }

    @Override
    public String uploadAvatar(String customerId, MultipartFile file) {
        try {
            String originalFileName = file.getOriginalFilename();
            String fileName = System.currentTimeMillis() + "_" + originalFileName;

            File uploadDir = new ClassPathResource("static/images/avatar").getFile();
            if (!uploadDir.exists()) uploadDir.mkdirs();

            Path path = Paths.get(uploadDir.getAbsolutePath(), fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            Customer customer = customerMapper.findCustomerByCustomerId(customerId);
            if (customer == null) {
                System.out.println("Lỗi: " + ErrorCode.CUSTOMER_NOT_FOUND);
                return null;
            }
            customer.setCustomerPicture(fileName);
            customerMapper.updateCustomer(customer);

            return customer.getCustomerPicture();
        } catch (IOException e) {
            throw new RuntimeException("Lỗi upload ảnh", e);
        }
    }

    @Override
    public int countAllCustomers() {
        return customerMapper.countCustomers();
    }
}
