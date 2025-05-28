package com.stonehnh.booking.mapper;

import com.stonehnh.booking.entity.BookingDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookingDetailMapper {

    /**
     * Lấy danh sách tất cả booking details trong bảng booking_details
     *
     * @return Danh sách thông tin của tất cả booking details
     */
    @Select("SELECT id, " +
            "booking_id, " +
            "homestay_id, " +
            "booking_time, " +
            "check_in_time, " +
            "check_out_time, " +
            "number_of_customers, " +
            "number_of_pets " +
            "FROM booking_details")
    List<BookingDetail> findAllBookingDetails();

    /**
     * Thêm một booking detail mới
     *
     * @param bookingDetail: Thông tin booking detail
     * @return Số dòng bị ảnh hưởng
     */
    @Insert("INSERT INTO booking_details (id, booking_id, homestay_id, booking_time, check_in_time, check_out_time, number_of_customers, number_of_pets) " +
            "VALUES (#{bookingDetail.id}, " +
            "#{bookingDetail.bookingId}, " +
            "#{bookingDetail.homestayId}, " +
            "#{bookingDetail.bookingTime}, " +
            "#{bookingDetail.checkInTime}, " +
            "#{bookingDetail.checkOutTime}, " +
            "#{bookingDetail.numberOfCustomers}, " +
            "#{bookingDetail.numberOfPets})")
    int insertBookingDetail(@Param("bookingDetail") BookingDetail bookingDetail);

    /**
     * Cập nhật thông tin booking detail
     *
     * @param bookingDetail: Entity đã có theo Id
     * @return Số dòng bị ảnh hưởng
     */
    @Update("UPDATE booking_details SET booking_id = #{bookingDetail.bookingId}, " +
            "homestay_id = #{bookingDetail.homestayId}, " +
            "booking_time = #{bookingDetail.bookingTime}, " +
            "check_in_time = #{bookingDetail.checkInTime}, " +
            "check_out_time = #{bookingDetail.checkOutTime}, " +
            "number_of_customers = #{bookingDetail.numberOfCustomers}, " +
            "number_of_pets = #{bookingDetail.numberOfPets} " +
            "WHERE id = #{bookingDetail.id}")
    int updateBookingDetail(@Param("bookingDetail") BookingDetail bookingDetail);

    /**
     * Xoá booking detail theo ID
     *
     * @param id: Mã id của booking detail cần xóa
     * @return Số dòng bị ảnh hưởng
     */
    @Delete("DELETE FROM booking_details WHERE id = #{id}")
    int deleteBookingDetail(@Param("id") String id);

    /**
     * Lấy thông tin booking detail theo ID
     *
     * @param id Mã booking detail cần tìm
     * @return Thông tin booking detail nếu tồn tại, ngược lại trả về null
     */
    @Select("SELECT id, " +
            "booking_id, " +
            "homestay_id, " +
            "booking_time, " +
            "check_in_time, " +
            "check_out_time, " +
            "number_of_customers, " +
            "number_of_pets " +
            "FROM booking_details " +
            "WHERE id = #{id}")
    BookingDetail findBookingDetailById(@Param("id") String id);

    /**
     * Kiểm tra sự tồn tại của booking detail theo ID
     *
     * @param id Mã booking detail cần kiểm tra
     * @return true nếu tồn tại, false nếu không
     */
    @Select("SELECT COUNT(*) > 0 FROM booking_details WHERE id = #{id}")
    boolean isExistedBookingDetailById(@Param("id") String id);
}
