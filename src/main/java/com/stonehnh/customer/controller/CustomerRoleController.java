package com.stonehnh.customer.controller;

import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.customer.dto.request.CreationCustomerRoleDto;
import com.stonehnh.customer.dto.response.CustomerRoleResponseDto;
import com.stonehnh.customer.entity.CustomerRole; // Vẫn giữ nếu bạn muốn dùng entity trực tiếp trong method signature, nhưng nên cân nhắc DTO
import com.stonehnh.customer.service.CustomerRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/customer-roles")
public class CustomerRoleController {

    private final CustomerRoleService customerRoleService;

    public CustomerRoleController(CustomerRoleService customerRoleService) {
        this.customerRoleService = customerRoleService;
    }

    /**
     * Lấy danh sách tất cả các gán vai trò của khách hàng.
     * GET /api/v1/customer-roles
     * @return ApiResponse chứa danh sách CustomerRoleResponseDto
     */
    @GetMapping
    public ApiResponse<Object> getAllCustomerRoles() {
        List<CustomerRoleResponseDto> customerRoles = customerRoleService.getAllCustomerRoles();
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách vai trò khách hàng thành công.")
                .data(customerRoles)
                .build();
    }

    /**
     * Gán một vai trò cho khách hàng.
     * POST /api/v1/customer-roles
     * @param request CreationCustomerRoleDto chứa customerId và roleId
     * @return ApiResponse với số dòng bị ảnh hưởng
     */
    @PostMapping
    public ApiResponse<Object> createNewCustomerRole(@RequestBody CreationCustomerRoleDto request) {
        try {
            int rowsAffected = customerRoleService.createNewCustomerRole(request);
            if (rowsAffected > 0) {
                return ApiResponse.builder()
                        .success(true)
                        .message("Gán vai trò cho khách hàng thành công.")
                        .data(rowsAffected)
                        .build();
            } else {
                return ApiResponse.builder()
                        .success(false)
                        .message("Vai trò đã được gán cho khách hàng này.")
                        .data(0)
                        .build();
            }
        } catch (NoSuchElementException e) {
            return ApiResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(0)
                    .build();
        }
    }

    /**
     * Cập nhật một gán vai trò cụ thể của khách hàng (thay đổi vai trò cũ sang vai trò mới).
     * PUT /api/v1/customer-roles/{customerId}
     * @param customerId ID của khách hàng
     * @return ApiResponse với số dòng bị ảnh hưởng
     */
    @PutMapping("/{customerId}")
    public ApiResponse<Object> updateCustomerRole(@PathVariable String customerId, @RequestBody CustomerRole customerRole) {
        try {
            int rowsAffected = customerRoleService.updateCustomerRole(customerId, customerRole);
            if (rowsAffected > 0) {
                return ApiResponse.builder()
                        .success(true)
                        .message("Cập nhật vai trò của khách hàng thành công.")
                        .data(rowsAffected)
                        .build();
            } else {
                return ApiResponse.builder()
                        .success(false)
                        .message("Không tìm thấy vai trò cũ để cập nhật hoặc vai trò mới trùng với vai trò cũ.")
                        .data(0)
                        .build();
            }
        } catch (NoSuchElementException e) {
            return ApiResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(0)
                    .build();
        } catch (IllegalArgumentException e) {
            return ApiResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(0)
                    .build();
        }
    }

    /**
     * Xóa tất cả các gán vai trò cho một khách hàng cụ thể.
     * DELETE /api/v1/customer-roles/{customerId}
     * @param customerId ID của khách hàng
     * @return ApiResponse với số dòng bị ảnh hưởng
     */
    @DeleteMapping("/{customerId}")
    public ApiResponse<Object> deleteCustomerRole(@PathVariable String customerId) {
        int rowsAffected = customerRoleService.deleteCustomerRole(customerId);
        if (rowsAffected > 0) {
            return ApiResponse.builder()
                    .success(true)
                    .message("Xóa vai trò của khách hàng thành công.")
                    .data(rowsAffected)
                    .build();
        } else {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy vai trò nào để xóa cho khách hàng này.")
                    .data(0)
                    .build();
        }
    }

    /**
     * Lấy thông tin vai trò của một khách hàng theo Customer ID.
     * Lưu ý: Phương thức này trong service hiện trả về một CustomerRoleResponseDto duy nhất.
     * Nếu một khách hàng có nhiều vai trò, bạn nên cân nhắc trả về List<CustomerRoleResponseDto>.
     * GET /api/v1/customer-roles/{customerId}/single
     * @param customerId ID của khách hàng
     * @return ApiResponse chứa CustomerRoleResponseDto
     */
    @GetMapping("/{customerId}/single")
    public ApiResponse<Object> getCustomerRoleByCustomerId(@PathVariable String customerId) {
        try {
            CustomerRoleResponseDto customerRole = customerRoleService.findCustomerRoleByCustomerId(customerId);
            return ApiResponse.builder()
                    .success(true)
                    .message("Lấy thông tin vai trò của khách hàng thành công.")
                    .data(customerRole)
                    .build();
        } catch (NoSuchElementException e) {
            return ApiResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }
}