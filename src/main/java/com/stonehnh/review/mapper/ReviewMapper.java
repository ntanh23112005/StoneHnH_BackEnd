package com.stonehnh.review.mapper;
import com.stonehnh.review.entity.Reviews;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface ReviewMapper {
    @Select("SELECT review_id, customer_id, homestay_id, review_comments, " +
            "rate_customer, created_time FROM reviews")
    List<Reviews> findAllReviews();
}
