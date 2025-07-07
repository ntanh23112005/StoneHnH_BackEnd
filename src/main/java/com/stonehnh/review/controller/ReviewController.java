package com.stonehnh.review.controller;

import com.stonehnh.common.handler.ApiResponse; // Assuming ApiResponse is available
import com.stonehnh.review.dto.request.CreationReviewDetailDto;
import com.stonehnh.review.dto.response.ReviewResponeDto;
import com.stonehnh.review.entity.Reviews; // Assuming Reviews entity is used in service method signature
import com.stonehnh.review.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Retrieves a list of all reviews.
     * GET /api/v1/reviews
     * @return ApiResponse containing a list of ReviewResponeDto
     */
    @GetMapping
    public ApiResponse<Object> getAllReviews() {
        List<ReviewResponeDto> reviews = reviewService.getAllReviews();
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách đánh giá tổng quát thành công.")
                .data(reviews)
                .build();
    }

    /**
     * Creates a new review.
     * POST /api/v1/reviews
     * @param request CreationReviewDetailDto containing new review details
     * @return ApiResponse with the number of affected rows
     */
    @PostMapping
    public ApiResponse<Object> createNewReview(@RequestBody CreationReviewDetailDto request) {
        int rowsAffected = reviewService.createNewReview(request);
        return ApiResponse.builder()
                .success(true)
                .message("Tạo đánh giá tổng quát mới thành công.")
                .data(rowsAffected)
                .build();
    }

    /**
     * Updates an existing review by its ID.
     * PUT /api/v1/reviews/{reviewId}
     * @param reviewId ID of the review to update
     * @param request Reviews entity containing updated data
     * @return ApiResponse with the number of affected rows
     */
    @PutMapping("/{reviewId}")
    public ApiResponse<Object> updateReview(@PathVariable int reviewId, @RequestBody Reviews request) {
        int rowsAffected = reviewService.updateReview(reviewId, request);
        if (rowsAffected > 0) {
            return ApiResponse.builder()
                    .success(true)
                    .message("Cập nhật đánh giá tổng quát thành công.")
                    .data(rowsAffected)
                    .build();
        } else {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy đánh giá tổng quát để cập nhật hoặc không có thay đổi.")
                    .data(0)
                    .build();
        }
    }

    /**
     * Deletes a review by its ID.
     * DELETE /api/v1/reviews/{reviewId}
     * @param reviewId ID of the review to delete
     * @return ApiResponse with the number of affected rows
     */
    @DeleteMapping("/{reviewId}")
    public ApiResponse<Object> deleteReviewById(@PathVariable int reviewId) {
        int rowsAffected = reviewService.deleteReviewById(reviewId);
        if (rowsAffected > 0) {
            return ApiResponse.builder()
                    .success(true)
                    .message("Xóa đánh giá tổng quát thành công.")
                    .data(rowsAffected)
                    .build();
        } else {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy đánh giá tổng quát để xóa.")
                    .data(0)
                    .build();
        }
    }

    /**
     * Finds a review by its ID.
     * GET /api/v1/reviews/{reviewId}
     * @param reviewId ID of the review to find
     * @return ApiResponse containing the ReviewResponeDto
     */
    @GetMapping("/{reviewId}")
    public ApiResponse<Object> findReviewById(@PathVariable int reviewId) {
        try {
            ReviewResponeDto review = reviewService.findReviewById(reviewId);
            return ApiResponse.builder()
                    .success(true)
                    .message("Lấy thông tin đánh giá tổng quát thành công.")
                    .data(review)
                    .build();
        } catch (NoSuchElementException e) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy đánh giá tổng quát với ID: " + reviewId)
                    .data(null)
                    .build();
        }
    }

    /**
     * Finds a list of reviews by homestay ID.
     * GET /api/v1/reviews/homestay/{homestayId}
     * @param homestayId ID of the homestay to find reviews for
     * @return ApiResponse containing a list of ReviewResponeDto
     */
    @GetMapping("/homestay/{homestayId}")
    public ApiResponse<Object> findReviewsByHomestayId(@PathVariable String homestayId) {
        List<ReviewResponeDto> reviews = reviewService.findReviewsByHomestayId(homestayId);
        if (reviews.isEmpty()) {
            return ApiResponse.builder()
                    .success(false)
                    .message("Không tìm thấy đánh giá tổng quát nào cho Homestay ID: " + homestayId)
                    .data(null)
                    .build();
        }
        return ApiResponse.builder()
                .success(true)
                .message("Lấy danh sách đánh giá tổng quát theo Homestay ID thành công.")
                .data(reviews)
                .build();
    }
}