package com.stonehnh.booking.service.impl;

import com.stonehnh.booking.converter.BookingConverter;
import com.stonehnh.booking.converter.BookingDetailConverter;
import com.stonehnh.booking.dto.request.CreationBookingDetailDto;
import com.stonehnh.booking.dto.request.CreationBookingDto;
import com.stonehnh.booking.dto.response.BookingResponseDto;
import com.stonehnh.booking.dto.response.BookingWithDetailDto;
import com.stonehnh.booking.entity.Booking;
import com.stonehnh.booking.entity.BookingDetail;
import com.stonehnh.booking.mapper.BookingDetailMapper;
import com.stonehnh.booking.mapper.BookingMapper;
import com.stonehnh.booking.service.BookingService;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import com.stonehnh.common.handler.ApiResponse;
import com.stonehnh.customer.converter.CustomerConverter;
import com.stonehnh.customer.entity.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;
    private final BookingDetailMapper bookingDetailMapper;

    public BookingServiceImpl(BookingMapper bookingMapper,BookingDetailMapper bookingDetailMapper){
        this.bookingMapper = bookingMapper;
        this.bookingDetailMapper = bookingDetailMapper;
    }

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
    public int updateBooking(String bookingId, CreationBookingDto creationBookingDto) {
        /*
         * TODO: Xử lý logic nếu có
         * */

        boolean isExistedBooking = bookingMapper.isExistedBookingById(bookingId);
        if (!isExistedBooking){
            // TODO: Xử lý theo nghiệp vụ
            throw new AppException(ErrorCode.BOOKING_NOT_FOUND);
        }
        return bookingMapper.updateBooking(BookingConverter.toEntity(creationBookingDto));
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

    @Override
    @Transactional
    public ApiResponse<?> createBookingWithDetail(CreationBookingDto creationBookingDto, CreationBookingDetailDto creationBookingDetailDto) {
        // Insert Booking
        String bookingId = UUID.randomUUID().toString();
        creationBookingDto.setBookingId(bookingId);
        int bookingResult = bookingMapper.insertBooking(BookingConverter.toEntity(creationBookingDto));
        if (bookingResult <= 0) {
            return ApiResponse.builder()
                    .success(false)
                    .message(ErrorCode.BOOKING_CREATE_FAILED.getMessage())
                    .data(null)
                    .build();
        }

        // Insert BookingDetail
        creationBookingDetailDto.setBookingId(bookingId);
        int detailResult = bookingDetailMapper.insertBookingDetail(BookingDetailConverter.toEntity(creationBookingDetailDto));
        if (detailResult <= 0) {
            return ApiResponse.builder()
                    .success(false)
                    .message(ErrorCode.BOOKING_CREATE_FAILED.getMessage())
                    .data(null)
                    .build();
        }

        // Thành công
        BookingDetail createdDetail = bookingDetailMapper.findBookingDetailById(creationBookingDetailDto.getId());
        return ApiResponse.builder()
                .success(true)
                .message("Tạo booking thành công")
                .data(createdDetail)
                .build();
    }

    @Override
    public int countBookings() {
        return bookingMapper.countBookings();
    }

    @Override
    public Double getTotalPaymentStatus() {
        return bookingMapper.getTotalPaymentStatus();
    }

    @Override
    public List<BookingWithDetailDto> getAllBookingsWithDetails() {
        return bookingMapper.findAllBookingsWithDetails();
    }
}
