package com.stonehnh.admin.mapper;

import com.stonehnh.admin.dto.response.BookingStatusDto;
import com.stonehnh.admin.dto.response.MonthlyRevenueDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

}
