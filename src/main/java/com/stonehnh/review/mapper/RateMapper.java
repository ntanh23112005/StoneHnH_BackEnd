package com.stonehnh.review.mapper;
import com.stonehnh.review.dto.response.RateResponseDto;
import com.stonehnh.review.entity.Rate;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface RateMapper {
    /**
     * Lấy danh sách tất cả đánh giá trong bảng rate
     *
     * @return Danh sách tất cả đánh giá
     */
    @Select("SELECT rate_id, homestay_id, customer_id, comments, rated_time, price, location, communication, " +
            "exactly, cleanliness_level, average_rate, rate_title FROM rates")
    List<Rate> findAllRates();

    /**
     * Thêm một đánh giá mới
     *
     * @param rate: Đối tượng Rate cần thêm
     * @return Số dòng bị ảnh hưởng
     */
    @Insert("INSERT INTO rates (rate_id, homestay_id, customer_id, comments, rated_time, price, location, " +
            "communication, exactly, cleanliness_level, average_rate, rate_title) " +
            "VALUES (#{rate.rateId}, #{rate.homestayId}, #{rate.customerId}, #{rate.comments}, #{rate.ratedTime}, " +
            "#{rate.price}, #{rate.location}, #{rate.communication}, #{rate.exactly}, #{rate.cleanlinessLevel}, " +
            "#{rate.averageRate}, #{rate.rateTitle})")
    int insertRate(@Param("rate") Rate rate);

    /**
     * Cập nhật thông tin đánh giá
     *
     * @param rate: Lấy entity đã có theo ID
     * @return Số dòng bị ảnh hưởng
     */
    @Update("UPDATE rates SET homestay_id = #{rate.homestayId}, customer_id = #{rate.customerId}, " +
            "comments = #{rate.comments}, rated_time = #{rate.ratedTime}, price = #{rate.price}, " +
            "location = #{rate.location}, communication = #{rate.communication}, exactly = #{rate.exactly}, " +
            "cleanliness_level = #{rate.cleanlinessLevel}, average_rate = #{rate.averageRate}, " +
            "rate_title = #{rate.rateTitle} WHERE rate_id = #{rate.rateId}")
    int updateRate(@Param("rate") Rate rate);

    /**
     * Xoá đánh giá theo ID
     *
     * @param rateId: Mã ID của đánh giá cần xoá
     * @return Số dòng bị ảnh hưởng
     */
    @Delete("DELETE FROM rates WHERE rate_id = #{rateId}")
    int deleteRate(@Param("rateId") String rateId);

    /**
     * Lấy thông tin đánh giá theo ID
     *
     * @param rateId Mã đánh giá cần tìm
     * @return Thông tin đánh giá nếu tồn tại, ngược lại trả về null
     */
    @Select("SELECT rate_id, homestay_id, customer_id, comments, rated_time, price, location, communication, " +
            "exactly, cleanliness_level, average_rate, rate_title FROM rates WHERE rate_id = #{rateId}")
    Rate findRateById(@Param("rateId") String rateId);

    /**
     * Kiểm tra sự tồn tại của đánh giá theo ID
     *
     * @param rateId Mã đánh giá cần kiểm tra
     * @return true nếu tồn tại, false nếu không
     */
    @Select("SELECT COUNT(*) > 0 FROM rates WHERE rate_id = #{rateId}")
    boolean isExistedRateById(@Param("rateId") String rateId);

    /**
     * Lấy thông tin đánh giá theo ID
     *
     * @param homestayId Mã đánh giá cần tìm
     * @return Thông tin đánh giá nếu tồn tại, ngược lại trả về null
     */
    @Select("SELECT r.rate_id, r.homestay_id, r.customer_id, r.comments, r.rated_time, " +
            "r.price, r.location, r.communication, " +
            "r.exactly, r.cleanliness_level, r.average_rate, r.rate_title, " +
            "c.customer_name, " +
            "c.customer_picture " +
            "FROM rates r " +
            "LEFT JOIN customers c on c.customer_id = r.customer_id  " +
            "WHERE r.homestay_id = #{homestayId}")
    List<RateResponseDto> findRateByHomestayId(@Param("homestayId") String homestayId);
}
