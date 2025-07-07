package com.stonehnh.review.controller;

import com.stonehnh.common.handler.ApiResponse; // Assuming ApiResponse is available
import com.stonehnh.review.dto.request.CreationRateDetailDto;
import com.stonehnh.review.dto.response.RateResponseDto;
import com.stonehnh.review.entity.Rate; // Assuming Rate entity is used in service method signature
import com.stonehnh.review.service.RateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/rates")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    /**
     * Retrieves a list of all rates.
     * GET /api/v1/rates
     * @return ApiResponse containing a list of RateResponseDto
     */
    @GetMapping
    public ApiResponse<Object> getAllRates() {
        List<RateResponseDto> rates = rateService.getAllRates();
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách đánh giá chi tiết thành công.")
                .data(rates)
                .build();
    }

    /**
     * Creates a new rate.
     * POST /api/v1/rates
     * @param request CreationRateDetailDto containing new rate details
     * @return ApiResponse with the number of affected rows
     */
    @PostMapping
    public ApiResponse<Object> createNewRate(@RequestBody CreationRateDetailDto request) {
        int rowsAffected = rateService.createNewRate(request);
        return ApiResponse.builder()
                .success(true)
                .message("Tạo đánh giá chi tiết mới thành công.")
                .data(rowsAffected)
                .build();
    }

    /**
     * Updates an existing rate by its ID.
     * PUT /api/v1/rates/{rateId}
     * @param rateId ID of the rate to update
     * @param request Rate entity containing updated data
     * @return ApiResponse with the number of affected rows
     */
    @PutMapping("/{rateId}")
    public ApiResponse<Object> updateRate(@PathVariable String rateId, @RequestBody Rate request) {
        int rowsAffected = rateService.updateRate(rateId, request);
        if (rowsAffected > 0) {
            return ApiResponse.builder()
                    .success(true)
                    .message("Cập nhật đánh giá chi tiết thành công.")
                    .data(rowsAffected)
                    .build();
        } else {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy đánh giá chi tiết để cập nhật hoặc không có thay đổi.")
                    .data(0)
                    .build();
        }
    }

    /**
     * Deletes a rate by its ID.
     * DELETE /api/v1/rates/{rateId}
     * @param rateId ID of the rate to delete
     * @return ApiResponse with the number of affected rows
     */
    @DeleteMapping("/{rateId}")
    public ApiResponse<Object> deleteRateById(@PathVariable String rateId) {
        int rowsAffected = rateService.deleteRateById(rateId);
        if (rowsAffected > 0) {
            return ApiResponse.builder()
                    .success(true)
                    .message("Xóa đánh giá chi tiết thành công.")
                    .data(rowsAffected)
                    .build();
        } else {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy đánh giá chi tiết để xóa.")
                    .data(0)
                    .build();
        }
    }

    /**
     * Finds a rate by its ID.
     * GET /api/v1/rates/{rateId}
     * @param rateId ID of the rate to find
     * @return ApiResponse containing the RateResponseDto
     */
    @GetMapping("/{rateId}")
    public ApiResponse<Object> findRateById(@PathVariable String rateId) {
        try {
            RateResponseDto rate = rateService.findRateById(rateId);
            return ApiResponse.builder()
                    .success(true)
                    .message("Lấy thông tin đánh giá chi tiết thành công.")
                    .data(rate)
                    .build();
        } catch (NoSuchElementException e) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy đánh giá chi tiết với ID: " + rateId)
                    .data(null)
                    .build();
        }
    }

    /**
     * Finds a list of rates by homestay ID.
     * GET /api/v1/rates/homestay/{homestayId}
     * @param homestayId ID of the homestay to find rates for
     * @return ApiResponse containing a list of RateResponseDto
     */
    @GetMapping("/homestay/{homestayId}")
    public ApiResponse<Object> findRateByHomestayId(@PathVariable String homestayId) {
        List<RateResponseDto> rates = rateService.findRateByHomestayId(homestayId);
        if (rates.isEmpty()) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy đánh giá chi tiết nào cho Homestay ID: " + homestayId)
                    .data(null)
                    .build();
        }
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách đánh giá chi tiết theo Homestay ID thành công.")
                .data(rates)
                .build();
    }
}