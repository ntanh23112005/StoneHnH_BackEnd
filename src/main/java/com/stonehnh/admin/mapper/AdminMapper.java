package com.stonehnh.admin.mapper;

import com.stonehnh.admin.dto.response.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    /**
     * Lấy doanh thu từng tháng theo năm
     * @param year năm cần lấy
     * @return MonthlyRevenueDto ( month, revenue)
     */
    @Select("""
        SELECT 
            MONTH(bd.booking_time) AS month,
            COALESCE(SUM(b.total_price), 0) AS revenue
        FROM bookings b
        JOIN booking_details bd ON b.booking_id = bd.booking_id
        WHERE b.payment_status = 1
          AND YEAR(bd.booking_time) = #{year}
        GROUP BY MONTH(bd.booking_time)
        ORDER BY month
    """)
    List<MonthlyRevenueDto> getMonthlyRevenue(@Param("year") Integer year);

    /**
     * Lấy số lượng booking theo trạng thái
     * @return BookingStatusDto (status, value)
     */
    @Select("""
        SELECT 
            b.payment_status AS status,
            COUNT(*) AS value
        FROM bookings b
        GROUP BY b.payment_status
        ORDER BY b.payment_status
    """)
    List<BookingStatusDto> getBookingStatusRatio();

    /**
     * Lấy danh sách tất cả homestay kèm thông tin liên quan
     */
    @Select("""
        SELECT 
            homestay_id,
            homestay_name,
            country_address,
            area_address,
            detail_address,
            daily_price,
            hourly_price,
            type,
            status,
            type_style,
            have_pet,
            max_customer,
            number_of_beds,
            number_of_bathrooms,
            conveniences,
            options,
            entrance_parking,
            bedroom_details,
            bathroom_details,
            support_equipments
        FROM homestays
        LIMIT #{limit} OFFSET #{offset}
    """)
    @Results({
            @Result(column = "homestay_id", property = "homestayId"),
            @Result(property = "images", column = "homestay_id",
                    many = @Many(select = "getImagesByHomestayId")),
            @Result(property = "rules", column = "homestay_id",
                    many = @Many(select = "getRulesByHomestayId")),
            @Result(property = "owners", column = "homestay_id",
                    many = @Many(select = "getOwnersByHomestayId")),
            @Result(property = "reviews", column = "homestay_id",
                    many = @Many(select = "getReviewsByHomestayId")),
            @Result(property = "rates", column = "homestay_id",
                    many = @Many(select = "getRatesByHomestayId"))
    })
    List<HomestayDto> getAllHomestays(@Param("limit") int limit, @Param("offset") int offset);

    // Lấy ảnh
    @Select("""
        SELECT homestay_image, image_for
        FROM homestay_images
        WHERE homestay_id = #{homestayId}
    """)
    List<HomestayImageDto> getImagesByHomestayId(String homestayId);

    // Lấy rule
    @Select("""
        SELECT rule_text, policy_text, created_at
        FROM homestay_rules
        WHERE homestay_id = #{homestayId}
    """)
    List<HomestayRuleDto> getRulesByHomestayId(String homestayId);

    // Lấy owner
    @Select("""
        SELECT customer_id, percentage_own
        FROM owners
        WHERE homestay_id = #{homestayId}
    """)
    List<OwnerDto> getOwnersByHomestayId(String homestayId);

    // Lấy reviews
    @Select("""
        SELECT customer_id, review_comments, rate_customer, created_time
        FROM reviews
        WHERE homestay_id = #{homestayId}
    """)
    List<ReviewDto> getReviewsByHomestayId(String homestayId);

    // Lấy rates
    @Select("""
        SELECT customer_id, comments, average_rate, rate_title
        FROM rates
        WHERE homestay_id = #{homestayId}
    """)
    List<RateDto> getRatesByHomestayId(String homestayId);

    /**
     * Cập nhật trạng thái của homestay
     * @param homestayId mã homestay cần cập nhật
     * @param status trạng thái mới (true/false)
     * @return số dòng bị ảnh hưởng
     */
    @Update("""
    UPDATE homestays
    SET status = #{status}
    WHERE homestay_id = #{homestayId}
""")
    int updateHomestayStatus(@Param("homestayId") String homestayId, @Param("status") boolean status);
}
