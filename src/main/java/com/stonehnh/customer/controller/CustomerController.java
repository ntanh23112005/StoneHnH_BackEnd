package com.stonehnh.customer.controller;

import com.stonehnh.customer.dto.request.CreationCustomerDto;
import com.stonehnh.customer.dto.request.CreationCustomerWithRoles;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.entity.Customer;
import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.customer.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController (CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public ApiResponse<Object> getAllCustomers(){
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách khách hàng thành công")
                .data(customerService.getAllCustomers())
                .build();
    }

    /**
     * Thêm customer mới
     */
    @PostMapping("/register")
    public ApiResponse<Object> createNewCustomer(@RequestBody CreationCustomerWithRoles request, HttpSession session) {
        CreationCustomerDto dto = request.getCreationCustomer();
        List<String> roleIds = request.getRoleIds();

        // Nếu không truyền roleIds => đang ĐĂNG KÝ
        if (roleIds == null) {
            // Kiểm tra xác thực email
            String verifiedEmail = (String) session.getAttribute("verifiedEmail");
            if (verifiedEmail == null || !verifiedEmail.equals(dto.getEmail())) {
                return ApiResponse.builder()
                        .success(false)
                        .message("Bạn cần xác thực email trước khi đăng ký.")
                        .data(null)
                        .build();
            }
            // Đăng ký => xóa xác thực email
            session.removeAttribute("verifiedEmail");
        }

        // Gọi service duy nhất
        CustomerResponseDto createdCustomer = customerService.createNewCustomerWithRole(dto, roleIds);

        return ApiResponse.builder()
                .success(true)
                .message(roleIds == null ? "Đăng ký thành công." : "Tạo khách hàng thành công.")
                .data(createdCustomer)
                .build();
    }

    /**
     * Cập nhật thông tin customer
     */
    @PutMapping
    public ResponseEntity<Integer> updateCustomer(@RequestParam String id, @RequestBody Customer customer) {
        return ResponseEntity.ok().body(customerService.updateCustomer(id, customer));
    }

    /**
     * Xóa customer theo Id
     */
    @DeleteMapping
    public ResponseEntity<Integer> deleteCustomer(@RequestParam String customerId) {
        return ResponseEntity.ok().body(customerService.deleteCustomerId(customerId));
    }

    /**
     * Thông tin customer theo customerId
     */
    @GetMapping("/{customerId}")
    public ApiResponse<Object> getCustomerByCustomerId(@PathVariable String customerId) {
        return ApiResponse.builder()
                .success(true)
                .message("Success")
                .data(customerService.findCustomerByCustomerId(customerId))
                .build();
    }

    /**
     * Gửi mã xác thực qua email và lưu trong session
     */
    @PostMapping("/send-verification-code")
    public ApiResponse<Object> sendVerificationCode(@RequestParam String email, HttpSession session) {
        String code = customerService.sendVerificationCodeToEmail(email);
        session.setAttribute("verificationCode", code);
        session.setAttribute("verificationEmail", email);

        return ApiResponse.builder()
                .success(true)
                .message("Mã xác thực đã được gửi về email.")
                .data(null)
                .build();
    }

    /**
     * Xác thực mã
     */
    @PostMapping("/verify-code")
    public ApiResponse<Object> verifyCode(@RequestParam String email,
                                          @RequestParam String code,
                                          HttpSession session) {
        System.out.println(">>>> SessionID = " + session.getId());
        System.out.println(">>>> Stored Email = " + session.getAttribute("verificationEmail"));
        System.out.println(">>>> Stored Code = " + session.getAttribute("verificationCode"));

        String storedEmail = (String) session.getAttribute("verificationEmail");
        String storedCode = (String) session.getAttribute("verificationCode");

        if (storedEmail == null || storedCode == null) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy mã xác thực. Vui lòng gửi lại.")
                    .data(null)
                    .build();
        }

        if (storedEmail.equals(email) && storedCode.equals(code)) {
            session.setAttribute("verifiedEmail", email);
            session.removeAttribute("verificationCode");
            session.removeAttribute("verificationEmail");
            return ApiResponse.builder()
                    .success(true)
                    .message("Xác thực thành công.")
                    .data(null)
                    .build();
        } else {
            return ApiResponse.builder()
                    .success(false)
                    .message("Mã xác thực không đúng.")
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/reset-password")
    public ApiResponse<Object> resetPassword(@RequestParam String email,
                                             @RequestParam String newPassword,
                                             HttpSession session) {
        String verifiedEmail = (String) session.getAttribute("verifiedEmail");

        if (verifiedEmail == null || !verifiedEmail.equals(email)) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Bạn cần xác thực email trước khi đặt lại mật khẩu.")
                    .data(null)
                    .build();
        }

        customerService.resetPassword(email, newPassword);

        // Xóa xác thực
        session.removeAttribute("verifiedEmail");

        return ApiResponse.builder()
                .success(true)
                .message("Đặt lại mật khẩu thành công.")
                .data(null)
                .build();
    }
}
