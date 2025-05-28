package com.stonehnh.review.mapper;
import com.stonehnh.review.entity.Rate;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface RateMapper {
    @Select("SELECT rate_id, homestay_id, customer_id, comments, rated_time, " +
            "price, location, communication, exactly, cleanliness_level, " +
            "average_rate, rate_title " +
            "FROM rates")
    List<Rate> findAllRates();
}
