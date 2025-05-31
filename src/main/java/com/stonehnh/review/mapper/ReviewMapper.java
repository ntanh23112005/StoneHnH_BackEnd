package com.stonehnh.review.mapper;
import com.stonehnh.review.entity.Reviews;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ReviewMapper {
    /**
     * Lấy danh sách tất cả reviews trong bảng reviews
     *
     * @return Danh sách thông tin của tất cả reviews
     */
    @Select("SELECT review_id, customer_id, homestay_id, review_comments, rate_customer, created_time FROM reviews")
    List<Reviews> findAllReviews();

    /**
     * Lấy thông tin review theo ID
     *
     * @param reviewId Mã customer cần tìm
     * @return Thông tin review nếu tồn tại, ngược lại trả về null
     */
    @Select("SELECT review_id, customer_id, homestay_id, review_comments, rate_customer, created_time " +
            "FROM reviews WHERE review_id = #{reviewId}")
    Reviews findReviewById(@Param("reviewId") int reviewId);

    /**
     * Thêm một review mới
     *
     * @return Số dòng bị ảnh hưởng
     */
    @Insert("INSERT INTO reviews (customer_id, homestay_id, review_comments, rate_customer, created_time) " +
            "VALUES (#{review.customerId}, #{review.homestayId}, #{review.reviewComments}, " +
            "#{review.rateCustomer}, #{review.createdTime})")
    @Options(useGeneratedKeys = true, keyProperty = "review.reviewId")
    int insertReview(@Param("review") Reviews review);

    /**
     * Cập nhật thông tin customer
     * @param review: Lấy Entity đã có theo Id
     * @return Số dòng bị ảnh hưởng
     */
    @Update("UPDATE reviews SET customer_id = #{review.customerId}, homestay_id = #{review.homestayId}, " +
            "review_comments = #{review.reviewComments}, rate_customer = #{review.rateCustomer}, " +
            "created_time = #{review.createdTime} WHERE review_id = #{review.reviewId}")
    int updateReview(@Param("review") Reviews review);

    /**
     * Xoá review theo ID
     * @param reviewId: Mã id của review cần xóa
     * @return Số dòng bị ảnh hưởng
     */
    @Delete("DELETE FROM reviews WHERE review_id = #{reviewId}")
    int deleteReview(@Param("reviewId") int reviewId);

    /**
     * Kiểm tra sự tồn tại của review theo ID
     *
     * @param reviewId Mã review cần kiểm tra
     * @return true nếu tồn tại, false nếu không
     */
    @Select("SELECT COUNT(*) > 0 FROM reviews WHERE review_id = #{reviewId}")
    boolean isExistedReviewById(@Param("reviewId") int reviewId);
}
