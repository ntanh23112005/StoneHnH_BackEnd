package com.stonehnh.review.service;

import com.stonehnh.review.dto.request.CreationReviewDetailDto;
import com.stonehnh.review.dto.response.ReviewResponeDto;
import com.stonehnh.review.entity.Reviews;

import java.util.List;

public interface ReviewService {
    /**
     * Lấy danh sách tất cả Reviews
     * @return Danh sách ReviewResponeDto
     */
    List<ReviewResponeDto> getAllReviews();

    /**
     * Tạo mới 1 Review
     * @param creationReviewDetailDto đối tượng DTO chứa dữ liệu tạo mới
     * @return Số dòng bị ảnh hưởng
     */
    int createNewReview(CreationReviewDetailDto creationReviewDetailDto);

    /**
     * Cập nhật 1 Review theo reviewId
     * @param reviewId mã Review cần cập nhật
     * @param review entity Reviews chứa dữ liệu mới
     * @return Số dòng bị ảnh hưởng
     */
    int updateReview(int reviewId, Reviews review);

    /**
     * Xóa 1 Review theo reviewId
     * @param reviewId mã Review cần xóa
     * @return Số dòng bị ảnh hưởng
     */
    int deleteReviewById(int reviewId);

    /**
     * Tìm 1 Review theo reviewId
     * @param reviewId mã Review cần tìm
     * @return ReviewResponeDto nếu tìm thấy, null nếu không
     */
    ReviewResponeDto findReviewById(int reviewId);

    /**
     * Tìm 1 Review theo homestayId
     * @param homestayId mã Review cần tìm
     * @return List ReviewResponeDto nếu tìm thấy, null nếu không
     */
    List<ReviewResponeDto> findReviewsByHomestayId(String homestayId);
}
