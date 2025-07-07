package com.stonehnh.booking.service;

import com.stonehnh.booking.dto.request.CreationBookingDetailDto;
import com.stonehnh.booking.dto.response.BookingDetailResponseDto;
import com.stonehnh.booking.entity.BookingDetail;

import java.util.List;

public interface BookingDetailService {

    /**
     * Lấy danh sách tất cả booking details
     * @return Danh sách booking details
     */
    List<BookingDetailResponseDto> getAllBookingDetails();

    /**
     * Tạo mới 1 BookingDetail
     * @return Số dòng bị ảnh hưởng
     */
    int createNewBookingDetail(CreationBookingDetailDto creationBookingDetailDto);

    /**
     * Cập nhật 1 BookingDetail
     * @return Số dòng bị ảnh hưởng
     */
    int updateBookingDetail(String id, BookingDetail bookingDetail);

    /**
     * Xóa 1 BookingDetail
     * @return Số dòng bị ảnh hưởng
     */
    int deleteBookingDetailById(String id);

    /**
     * Tìm 1 booking detail theo id
     * @return 1 booking detail đã tìm
     */
    BookingDetailResponseDto findBookingDetailById(String id);

    /**
     * Tìm 1 booking detail theo id
     * @return 1 booking detail đã tìm
     */
    List<BookingDetailResponseDto> findBookingDetailByHomestayId(String homestayId);
}
