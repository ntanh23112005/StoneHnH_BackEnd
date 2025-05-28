package com.stonehnh.booking.converter;

import com.stonehnh.booking.dto.request.CreationBookingDto;
import com.stonehnh.booking.dto.response.BookingResponseDto;
import com.stonehnh.booking.entity.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingConverter {

    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return List bookings đã được convert từ entity -> dto
     */
    public static List<BookingResponseDto> toDtoList(List<Booking> bookings){
        List<BookingResponseDto> dtoList = new ArrayList<>();

        for (Booking b : bookings){
            BookingResponseDto dto = new BookingResponseDto();
            dto.setBookingId(b.getBookingId());
            dto.setCustomerId(b.getCustomerId());
            dto.setTotalPrice(b.getTotalPrice());
            dto.setPaymentStatus(b.getPaymentStatus());

            dtoList.add(dto);
        }

        return dtoList;
    }

    /**
     * Hàm convert data từ database lên thành dto để lên frontend
     *
     * @return 1 booking đã được convert từ entity -> dto
     */
    public static BookingResponseDto toDto(Booking booking){
        return BookingResponseDto.builder()
                                .bookingId(booking.getBookingId())
                                .customerId(booking.getCustomerId())
                                .totalPrice(booking.getTotalPrice())
                                .paymentStatus(booking.getPaymentStatus())
                                .build();
    }

    /**
     * Hàm convert data từ frontend xuống entity backend để làm việc với database
     *
     * @return 1 bookinng đã được convert từ dto -> entity
     */
    public static Booking toEntity(CreationBookingDto bookingDto){
        Booking booking = new Booking();
        booking.setBookingId(bookingDto.getBookingId());
        booking.setCustomerId(bookingDto.getCustomerId());
        booking.setTotalPrice(bookingDto.getTotalPrice());
        booking.setPaymentStatus(bookingDto.getPaymentStatus());

        return booking;
    }
}
