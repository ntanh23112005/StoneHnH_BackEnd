package com.stonehnh.owner.mapper;

import com.stonehnh.owner.dto.response.*;
import com.stonehnh.owner.entity.Owner;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OwnerMapper {
    /**
     * Lấy danh sách tất cả chủ sở hữu homestay.
     *
     * @return Danh sách các đối tượng Owner.
     */
    @Select("SELECT customer_id, homestay_id, percentage_own FROM owners")
    List<Owner> findAllOwners();

    /**
     * Lấy danh sách các homestay mà một khách hàng đang sở hữu.
     *
     * @param customerId Mã khách hàng.
     * @return Danh sách các đối tượng Owner.
     */
    @Select("SELECT customer_id, homestay_id, percentage_own FROM owners WHERE customer_id = #{customerId}")
    List<Owner> findOwnersByCustomerId(@Param("customerId") String customerId);

    /**
     * Lấy danh sách chủ sở hữu của một homestay.
     *
     * @param homestayId Mã homestay.
     * @return Danh sách các đối tượng Owner.
     */
    @Select("SELECT customer_id, homestay_id, percentage_own FROM owners WHERE homestay_id = #{homestayId}")
    Owner findOwnersByHomestayId(@Param("homestayId") String homestayId);

    /**
     * Lấy thông tin sở hữu cụ thể theo mã khách hàng và homestay.
     *
     * @param customerId Mã khách hàng.
     * @param homestayId Mã homestay.
     * @return Đối tượng Owner nếu tồn tại, null nếu không.
     */
    @Select("SELECT customer_id, homestay_id, percentage_own FROM owners " +
            "WHERE customer_id = #{customerId} AND homestay_id = #{homestayId}")
    Owner findOwner(@Param("customerId") String customerId, @Param("homestayId") String homestayId);

    /**
     * Thêm mới một quan hệ sở hữu homestay.
     *
     * @param owner Đối tượng Owner chứa thông tin cần thêm.
     * @return Số dòng bị ảnh hưởng.
     */
    @Insert("INSERT INTO owners (customer_id, homestay_id, percentage_own) " +
            "VALUES (#{owner.customerId}, #{owner.homestayId}, #{owner.percentageOwn})")
    int insertOwner(@Param("owner") Owner owner);

    /**
     * Cập nhật phần trăm sở hữu của một khách hàng đối với một homestay.
     *
     * @param owner Đối tượng Owner chứa thông tin cập nhật.
     * @return Số dòng bị ảnh hưởng.
     */
    @Update("UPDATE owners SET percentage_own = #{owner.percentageOwn} " +
            "WHERE customer_id = #{owner.customerId} AND homestay_id = #{owner.homestayId}")
    int updateOwner(@Param("owner") Owner owner);

    /**
     * Xóa một quan hệ sở hữu giữa khách hàng và homestay.
     *
     * @param customerId Mã khách hàng.
     * @param homestayId Mã homestay.
     * @return Số dòng bị ảnh hưởng.
     */
    @Delete("DELETE FROM owners WHERE customer_id = #{customerId} AND homestay_id = #{homestayId}")
    int deleteOwner(@Param("customerId") String customerId, @Param("homestayId") String homestayId);

    /**
     * Xóa tất cả chủ sở hữu của một homestay.
     *
     * @param homestayId Mã homestay.
     * @return Số dòng bị ảnh hưởng.
     */
    @Delete("DELETE FROM owners WHERE homestay_id = #{homestayId}")
    int deleteOwnersByHomestayId(@Param("homestayId") String homestayId);

    /**
     * Kiểm tra xem có tồn tại Owner nào trong bảng Owner với customerId được cung cấp hay không.
     *
     * @param customerId Mã khách hàng cần kiểm tra sự tồn tại trong bảng Owner.
     * @return true nếu tồn tại ít nhất một bản ghi Owner với customerId tương ứng, ngược lại trả về false.
     */
    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM Owner WHERE customerId = #{customerId}")
    boolean existsOwner(@Param("customerId") String customerId);

    @Select("""
    SELECT 
        (SELECT IFNULL(SUM(b.total_price), 0)
         FROM owners o
         JOIN booking_details bd ON o.homestay_id = bd.homestay_id
         JOIN bookings b ON bd.booking_id = b.booking_id
         JOIN payments p ON b.booking_id = p.booking_id
         WHERE o.customer_id = #{customerId}
           AND p.status = 1) AS totalRevenue,

        (SELECT COUNT(*) 
         FROM owners o
         JOIN homestays h ON o.homestay_id = h.homestay_id
         WHERE o.customer_id = #{customerId}
           AND h.status = TRUE) AS activeHomestays,

        (SELECT COUNT(*) 
         FROM owners o
         JOIN homestays h ON o.homestay_id = h.homestay_id
         WHERE o.customer_id = #{customerId}
           AND h.status = FALSE) AS inactiveHomestays,

        (SELECT COUNT(DISTINCT b.booking_id)
         FROM owners o
         JOIN booking_details bd ON o.homestay_id = bd.homestay_id
         JOIN bookings b ON bd.booking_id = b.booking_id
         JOIN payments p ON b.booking_id = p.booking_id
         WHERE o.customer_id = #{customerId}
           AND p.status = 0) AS bookingStatus0,

        (SELECT COUNT(DISTINCT b.booking_id)
         FROM owners o
         JOIN booking_details bd ON o.homestay_id = bd.homestay_id
         JOIN bookings b ON bd.booking_id = b.booking_id
         JOIN payments p ON b.booking_id = p.booking_id
         WHERE o.customer_id = #{customerId}
           AND p.status = 1) AS bookingStatus1,

        (SELECT COUNT(DISTINCT b.booking_id)
         FROM owners o
         JOIN booking_details bd ON o.homestay_id = bd.homestay_id
         JOIN bookings b ON bd.booking_id = b.booking_id
         JOIN payments p ON b.booking_id = p.booking_id
         WHERE o.customer_id = #{customerId}
           AND p.status = 2) AS bookingStatus2
    """)
    OwnerStatisticsDto getOwnerStatistics(@Param("customerId") String customerId);

    @Select("""
        SELECT 
            b.booking_id,
            b.customer_id,
            b.total_price,
            b.payment_status,
            bd.booking_time,
            bd.check_in_time,
            bd.check_out_time,
            bd.number_of_customers,
            bd.number_of_pets,
            h.homestay_id,
            h.homestay_name
        FROM 
            owners o
            JOIN booking_details bd ON o.homestay_id = bd.homestay_id
            JOIN bookings b ON bd.booking_id = b.booking_id
            JOIN homestays h ON bd.homestay_id = h.homestay_id
        WHERE
            o.customer_id = #{customerId}
        ORDER BY
            bd.booking_time DESC
    """)
    List<OwnerBookingDetailDto> getAllBookingDetailsByOwner(@Param("customerId") String customerId);

    @Select("""
        SELECT DISTINCT
            b.booking_id,
            b.customer_id,
            b.total_price,
            b.payment_status
        FROM
            owners o
            JOIN booking_details bd ON o.homestay_id = bd.homestay_id
            JOIN bookings b ON bd.booking_id = b.booking_id
        WHERE
            o.customer_id = #{customerId}
    """)
    List<OwnerBookingDto> getAllBookingsByOwner(@Param("customerId") String customerId);

    @Select("""
    SELECT 
        MONTH(bd.check_out_time) AS month,
        IFNULL(SUM(b.total_price), 0) AS revenue
    FROM owners o
    JOIN booking_details bd ON o.homestay_id = bd.homestay_id
    JOIN bookings b ON bd.booking_id = b.booking_id
    JOIN payments p ON b.booking_id = p.booking_id
    WHERE o.customer_id = #{customerId}
      AND p.status = 1
    GROUP BY MONTH(bd.check_out_time)
    ORDER BY month
""")
    List<MonthlyRevenueOwnerDto> getMonthlyRevenue(@Param("customerId") String customerId);

    @Select("""
    SELECT 
        h.homestay_id,
        h.homestay_name,
        h.country_address,
        h.area_address,
        h.detail_address,
        h.daily_price,
        h.hourly_price,
        h.type,
        h.status,
        h.type_style,
        h.have_pet,
        h.max_customer,
        h.number_of_beds,
        h.number_of_bathrooms,
        h.conveniences,
        h.options,
        h.entrance_parking,
        h.bedroom_details,
        h.bathroom_details,
        h.support_equipments,
        hi.homestay_image,
        hi.image_for
    FROM owners o
    JOIN homestays h ON o.homestay_id = h.homestay_id
    LEFT JOIN homestay_images hi ON h.homestay_id = hi.homestay_id
    WHERE o.customer_id = #{customerId}
""")
    List<OwnerHomestayDto> getOwnedHomestaysWithImages(@Param("customerId") String customerId);
}
