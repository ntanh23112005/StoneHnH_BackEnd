package com.stonehnh.mapper;

import com.stonehnh.entity.Booking;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookingMapper {

    /**
     * Lấy danh sách tất cả bookings trong bảng bookings
     *
     * @return Danh sách thông tin của tất cả bookings
     */
    @Select("SELECT booking_id, " +
                    "customer_id, " +
                    "total_price, " +
                    "payment_status " +
            "FROM bookings")
    List<Booking> findAllBookings();

    /**
     * Thêm một booking mới
     *
     * @return Số dòng bị ảnh hưởng
     */
    @Insert("INSERT INTO bookings (booking_id, customer_id, total_price, payment_status)" +
                            "VALUES (#{booking.bookingId}," +
                                    "#{booking.customerId}," +
                                    "#{booking.totalPrice}," +
                                    "#{booking.paymentStatus}) ")
    int insertBooking(@Param("booking") Booking booking);

    /**
     * Cập nhật thông tin customer
     * @param booking: Lấy Entity đã có theo Id
     * @return Số dòng bị ảnh hưởng
     */
    @Update("UPDATE bookings SET booking_id = #{booking.bookingId}," +
                                "customer_id = #{booking.customerId}," +
                                "total_price = #{booking.totalPrice}," +
                                "payment_status = #{booking.paymentStatus}" +
            "WHERE booking_id = #{booking.bookingId}")
    int updateBooking(@Param("booking") Booking booking);

    /**
     * Xoá booking theo ID
     * @param bookingId: Mã id của booking cần xóa
     * @return Số dòng bị ảnh hưởng
     */
    @Delete("DELETE FROM bookings" +
            "WHERE booking_id = #{bookingId}")
    int deleteBoooking(@Param("bookingId") String bookingId);

    /**
     * Lấy thông tin booking theo ID
     *
     * @param bookingId Mã customer cần tìm
     * @return Thông tin booking nếu tồn tại, ngược lại trả về null
     */
    @Select("SELECT booking_id, " +
                    "customer_id, " +
                    "total_price, " +
                    "payment_status " +
            "FROM bookings" +
            "WHERE booking_id = #{bookingId}")
    Booking findBookingByBookingId(@Param("bookingId") String bookingId);

    /**
     * Kiểm tra sự tồn tại của booking theo ID
     *
     * @param bookingId Mã booking cần kiểm tra
     * @return true nếu tồn tại, false nếu không
     */
    @Select("SELECT COUNT(*) > 0 FROM bookings WHERE bookingId = #{bookingId}")
    boolean isExistedBookingById(@Param("bookingId") String bookingId);
}
