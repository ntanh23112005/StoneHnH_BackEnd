package com.stonehnh.booking.service.impl;

import com.stonehnh.booking.converter.BookingDetailConverter;
import com.stonehnh.booking.dto.request.CreationBookingDetailDto;
import com.stonehnh.booking.dto.response.BookingDetailResponseDto;
import com.stonehnh.booking.entity.BookingDetail;
import com.stonehnh.booking.mapper.BookingDetailMapper;
import com.stonehnh.booking.service.BookingDetailService;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.exception.AppException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingDetailServiceImpl implements BookingDetailService {

    private final BookingDetailMapper bookingDetailMapper;

    public BookingDetailServiceImpl(BookingDetailMapper bookingDetailMapper) {
        this.bookingDetailMapper = bookingDetailMapper;
    }

    @Override
    public List<BookingDetailResponseDto> getAllBookingDetails() {
        List<BookingDetail> bookingDetailList = bookingDetailMapper.findAllBookingDetails();
        return BookingDetailConverter.toDtoList(bookingDetailList);
    }

    @Override
    public int createNewBookingDetail(CreationBookingDetailDto creationBookingDetailDto) {
        BookingDetail bookingDetail = BookingDetailConverter.toEntity(creationBookingDetailDto);
        bookingDetail.setId(String.valueOf(UUID.randomUUID()));
        return bookingDetailMapper.insertBookingDetail(bookingDetail);
    }

    @Override
    public int updateBookingDetail(String id, BookingDetail bookingDetail) {
        boolean isExisted = bookingDetailMapper.isExistedBookingDetailById(id);
        if (!isExisted) {
            throw new AppException(ErrorCode.BOOKING_NOT_FOUND);
        }
        return bookingDetailMapper.updateBookingDetail(bookingDetail);
    }

    @Override
    public int deleteBookingDetailById(String id) {
        boolean isExisted = bookingDetailMapper.isExistedBookingDetailById(id);
        if (!isExisted) {
            throw new AppException(ErrorCode.BOOKING_NOT_FOUND);
        }
        return bookingDetailMapper.deleteBookingDetail(id);
    }

    @Override
    public BookingDetailResponseDto findBookingDetailById(String id) {
        BookingDetail bookingDetail = bookingDetailMapper.findBookingDetailById(id);
        if (bookingDetail == null) {
            throw new AppException(ErrorCode.BOOKING_NOT_FOUND);
        }
        return BookingDetailConverter.toDto(bookingDetail);
    }
}
