package com.stonehnh.booking.service;

import com.stonehnh.booking.dto.request.CreationBookingDto;
import com.stonehnh.booking.dto.response.BookingResponseDto;
import com.stonehnh.booking.entity.Booking;
import com.stonehnh.customer.dto.request.CreationCustomerDto;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.entity.Customer;

import java.util.List;

public interface BookingService {

    /**
     * Lấy danh sách tất cả bookings
     * @return Danh sách bookings
     */
    List<BookingResponseDto> getAllBookings();

    /**
     * Tạo mới 1 Booking
     * @return Số dòng bị ảnh hưởng
     */
    int createNewBooking(CreationBookingDto creationBookingDto);

    /**
     * Cập nhật 1 Booking
     * @return Số dòng bị ảnh hưởng
     */
    int updateBooking(String bookingId, Booking booking);

    /**
     * Xóa 1 Booking
     * @return Số dòng bị ảnh hưởng
     */
    int deleteBookingById(String bookingId);

    /**
     * Tìm 1 booking theo Booking Id
     * @return 1 booking theo id đã tìm
     */
    BookingResponseDto findBookingByBookingId (String bookingId);
}
