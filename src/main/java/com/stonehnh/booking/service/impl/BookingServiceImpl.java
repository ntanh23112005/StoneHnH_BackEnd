package com.stonehnh.booking.service.impl;

import com.stonehnh.booking.converter.BookingConverter;
import com.stonehnh.booking.dto.request.CreationBookingDto;
import com.stonehnh.booking.dto.response.BookingResponseDto;
import com.stonehnh.booking.entity.Booking;
import com.stonehnh.booking.mapper.BookingMapper;
import com.stonehnh.booking.service.BookingService;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.customer.converter.CustomerConverter;
import com.stonehnh.customer.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingMapper bookingMapper){this.bookingMapper = bookingMapper;}

    @Override
    public List<BookingResponseDto> getAllBookings() {

        List<Booking> bookingList = bookingMapper.findAllBookings();

//        List<BookingResponseDto> bookingResponseDtos = BookingConverter.toDtoList(bookingList);

        return BookingConverter.toDtoList(bookingList);
    }

    @Override
    public int createNewBooking(CreationBookingDto creationBookingDto) {
        /*
         * TODO: Xử lý logic riêng (nếu có)
         *
         * TODO: Convert from dto to entity to insert database
         *
         * */

        Booking booking = BookingConverter.toEntity(creationBookingDto);
        booking.setBookingId(String.valueOf(UUID.randomUUID()));
        return bookingMapper.insertBooking(booking);
    }

    @Override
    public int updateBooking(String bookingId, Booking booking) {
        /*
         * TODO: Xử lý logic nếu có
         * */

        boolean isExistedBooking = bookingMapper.isExistedBookingById(bookingId);
        if (!isExistedBooking){
            // TODO: Xử lý theo nghiệp vụ
            throw new AppException(ErrorCode.BOOKING_NOT_FOUND);
        }
        return bookingMapper.updateBooking(booking);
    }

    @Override
    public int deleteBookingById(String bookingId) {
        /*
         * TODO: Xử lý logic nếu có
         * */

        boolean isExistedBooking = bookingMapper.isExistedBookingById(bookingId);
        if (!isExistedBooking){
            // TODO: Xử lý theo nghiệp vụ
            throw new AppException(ErrorCode.BOOKING_NOT_FOUND);
        }
        return bookingMapper.deleteBoooking(bookingId);
    }

    @Override
    public BookingResponseDto findBookingByBookingId(String bookingId) {
        Booking booking = bookingMapper.findBookingByBookingId(bookingId);

        /*
         * TODO: Xử lý logic nếu cần
         * */

        if (booking == null){
            throw new AppException(ErrorCode.BOOKING_NOT_FOUND);
        }
        return BookingConverter.toDto(booking);
    }
}
