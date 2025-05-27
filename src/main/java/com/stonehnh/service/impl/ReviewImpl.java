package com.stonehnh.service.impl;

import com.stonehnh.converter.ReviewConverter;
import com.stonehnh.dto.response.ReviewResponseDto;
import com.stonehnh.entity.Reviews;
import com.stonehnh.mapper.ReviewMapper;
import com.stonehnh.service.ReviewService;

import java.util.List;

public class ReviewImpl implements ReviewService {

    private  final ReviewMapper reviewMapper;

    public ReviewImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewResponseDto> getAllReview() {
        List<Reviews> reviewsList = reviewMapper.findAllReviews();

        List<ReviewResponseDto> responseDtos = ReviewConverter.toDtoList(reviewsList);
        return responseDtos;
    }
}
