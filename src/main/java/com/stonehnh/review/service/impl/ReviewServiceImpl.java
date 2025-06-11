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

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewResponeDto> getAllReviews() {
        List<Reviews> reviewList = reviewMapper.findAllReviews();
        return ReviewConverter.toDtoList(reviewList);
    }

    @Override
    public int createNewReview(CreationReviewDetailDto creationReviewDetailDto) {
        Reviews review = ReviewConverter.toEntity(creationReviewDetailDto);
        return reviewMapper.insertReview(review);
    }

    @Override
    public int updateReview(int reviewId, Reviews review) {
        boolean isExistedReview = reviewMapper.isExistedReviewById(reviewId);
        if (!isExistedReview) {
            throw new AppException(ErrorCode.REVIEW_NOT_FOUND);
        }
        return reviewMapper.updateReview(review);
    }

    @Override
    public int deleteReviewById(int reviewId) {
        boolean isExistedReview = reviewMapper.isExistedReviewById(reviewId);
        if (!isExistedReview) {
            throw new AppException(ErrorCode.REVIEW_NOT_FOUND);
        }
        return reviewMapper.deleteReview(reviewId);
    }

    @Override
    public ReviewResponeDto findReviewById(int reviewId) {
        Reviews review = reviewMapper.findReviewById(reviewId);
        if (review == null) {
            throw new AppException(ErrorCode.REVIEW_NOT_FOUND);
        }
        return ReviewConverter.toDto(review);
    }
}
