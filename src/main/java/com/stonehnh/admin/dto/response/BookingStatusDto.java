package com.stonehnh.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingStatusDto {
    // 0: Đang chờ thanh toán, 1: Hoàn tất, 2: Đã hủy
    private int status;
    private long value;   // số lượng đơn
}
