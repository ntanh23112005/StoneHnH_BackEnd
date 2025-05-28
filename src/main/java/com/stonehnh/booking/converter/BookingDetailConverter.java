package com.stonehnh.booking.converter;

import com.stonehnh.booking.dto.request.CreationBookingDetailDto;
import com.stonehnh.booking.dto.response.BookingDetailResponseDto;
import com.stonehnh.booking.entity.BookingDetail;

import java.util.ArrayList;
import java.util.List;

public class BookingDetailConverter {
    /**
     * Convert list entity -> list dto để gửi lên frontend
     *
     * @param bookingDetails danh sách entity
     * @return danh sách dto
     */
    public static List<BookingDetailResponseDto> toDtoList(List<BookingDetail> bookingDetails) {
        List<BookingDetailResponseDto> dtoList = new ArrayList<>();

        for (BookingDetail bd : bookingDetails) {
            BookingDetailResponseDto dto = new BookingDetailResponseDto();
            dto.setId(bd.getId());
            dto.setBookingId(bd.getBookingId());
            dto.setHomestayId(bd.getHomestayId());
            dto.setBookingTime(bd.getBookingTime());
            dto.setCheckInTime(bd.getCheckInTime());
            dto.setCheckOutTime(bd.getCheckOutTime());
            dto.setNumberOfCustomers(bd.getNumberOfCustomers());
            dto.setNumberOfPets(bd.getNumberOfPets());

            dtoList.add(dto);
        }

        return dtoList;
    }

    /**
     * Convert 1 entity -> dto để gửi lên frontend
     *
     * @param bookingDetail entity
     * @return dto
     */
    public static BookingDetailResponseDto toDto(BookingDetail bookingDetail) {
        return BookingDetailResponseDto.builder()
                .id(bookingDetail.getId())
                .bookingId(bookingDetail.getBookingId())
                .homestayId(bookingDetail.getHomestayId())
                .bookingTime(bookingDetail.getBookingTime())
                .checkInTime(bookingDetail.getCheckInTime())
                .checkOutTime(bookingDetail.getCheckOutTime())
                .numberOfCustomers(bookingDetail.getNumberOfCustomers())
                .numberOfPets(bookingDetail.getNumberOfPets())
                .build();
    }

    /**
     * Convert 1 dto -> entity để làm việc với database
     *
     * @param bookingDetailDto dto từ frontend
     * @return entity
     */
    public static BookingDetail toEntity(CreationBookingDetailDto bookingDetailDto) {
        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setId(bookingDetailDto.getId());
        bookingDetail.setBookingId(bookingDetailDto.getBookingId());
        bookingDetail.setHomestayId(bookingDetailDto.getHomestayId());
        bookingDetail.setBookingTime(bookingDetailDto.getBookingTime());
        bookingDetail.setCheckInTime(bookingDetailDto.getCheckInTime());
        bookingDetail.setCheckOutTime(bookingDetailDto.getCheckOutTime());
        bookingDetail.setNumberOfCustomers(bookingDetailDto.getNumberOfCustomers());
        bookingDetail.setNumberOfPets(bookingDetailDto.getNumberOfPets());

        return bookingDetail;
    }
}
