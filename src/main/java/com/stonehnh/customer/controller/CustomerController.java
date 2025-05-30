package com.stonehnh.customer.controller;

import com.stonehnh.customer.dto.request.CreationCustomerDto;
import com.stonehnh.customer.entity.Customer;
import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ApiResponse<Object> createNewCustomer(@RequestBody CreationCustomerDto creationCustomerDto) {
        return ApiResponse.builder()
                .success(true)
                .message("Success")
                .data(customerService.createNewCustomer(creationCustomerDto))
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

}
