package com.stonehnh.review.converter;

import com.stonehnh.review.dto.request.CreationReviewDetailDto;
import com.stonehnh.review.dto.response.ReviewResponeDto;
import com.stonehnh.review.entity.Reviews;

import java.util.ArrayList;
import java.util.List;

public class ReviewConverter {
    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return List Review đã được convert từ entity -> dto
     */
    public static List<ReviewResponeDto> toDtoList(List<Reviews> reviews) {
        List<ReviewResponeDto> dtoList = new ArrayList<>();

        for (Reviews r : reviews) {
            ReviewResponeDto dto = new ReviewResponeDto();
            dto.setReviewId(r.getReviewId());
            dto.setCustomerId(r.getCustomerId());
            dto.setHomestayId(r.getHomestayId());
            dto.setReviewComments(r.getReviewComments());
            dto.setRateCustomer(r.getRateCustomer());
            dto.setCreatedTime(r.getCreatedTime());

            dtoList.add(dto);
        }

        return dtoList;
    }
    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return 1 reviesw đã được convert từ entity -> dto
     */
    public static ReviewResponeDto toDto(Reviews review) {
        return ReviewResponeDto.builder()
                .reviewId(review.getReviewId())
                .customerId(review.getCustomerId())
                .homestayId(review.getHomestayId())
                .reviewComments(review.getReviewComments())
                .rateCustomer(review.getRateCustomer())
                .createdTime(review.getCreatedTime())
                .build();
    }
    /**
     * Hàm convert data từ frontend xuống entity backend để làm việc với database
     *
     * @return 1 review đã được convert từ dto -> entity
     */
    public static Reviews toEntity(CreationReviewDetailDto dto) {
        Reviews review = new Reviews();
        review.setReviewId(dto.getReviewId());
        review.setCustomerId(dto.getCustomerId());
        review.setHomestayId(dto.getHomestayId());
        review.setReviewComments(dto.getReviewComments());
        review.setRateCustomer(dto.getRateCustomer());
        review.setCreatedTime(dto.getCreatedTime());
        return review;
    }
}
