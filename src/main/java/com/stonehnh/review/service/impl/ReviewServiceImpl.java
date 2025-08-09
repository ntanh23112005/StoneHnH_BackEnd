package com.stonehnh.review.service.impl;

import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.review.converter.ReviewConverter;
import com.stonehnh.review.dto.request.CreationReviewDetailDto;
import com.stonehnh.review.dto.response.ReviewResponeDto;
import com.stonehnh.review.entity.Reviews;
import com.stonehnh.review.mapper.ReviewMapper;
import com.stonehnh.review.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponeDto> getAllReviews() {
        try {
            return ReviewConverter.toDtoList(reviewMapper.findAllReviews());
        } catch (Exception e) {
            throw new AppException(ErrorCode.REVIEW_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public int createNewReview(CreationReviewDetailDto dto) {
        try {
            Reviews review = ReviewConverter.toEntity(dto);
            return reviewMapper.insertReview(review);
        } catch (Exception e) {
            throw new AppException(ErrorCode.RATE_CREATE_FAILED); // Nếu có lỗi riêng cho REVIEW thì thay thế
        }
    }

    @Override
    @Transactional
    public int updateReview(int reviewId, Reviews review) {
        if (!reviewMapper.isExistedReviewById(reviewId)) {
            throw new AppException(ErrorCode.REVIEW_NOT_FOUND);
        }
        return reviewMapper.updateReview(review);
    }

    @Override
    @Transactional
    public int deleteReviewById(int reviewId) {
        if (!reviewMapper.isExistedReviewById(reviewId)) {
            throw new AppException(ErrorCode.REVIEW_NOT_FOUND);
        }
        return reviewMapper.deleteReview(reviewId);
    }

    @Override
    public ReviewResponeDto findReviewById(int reviewId) {
        return Optional.ofNullable(reviewMapper.findReviewById(reviewId))
                .map(ReviewConverter::toDto)
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));
    }

    @Override
    public List<ReviewResponeDto> findReviewsByHomestayId(String homestayId) {
        List<Reviews> list = reviewMapper.findReviewsByHomestayId(homestayId);
        if (list == null || list.isEmpty()) {
            return ReviewConverter.toDtoList(Collections.emptyList());
        }
        return ReviewConverter.toDtoList(list);
    }

}
