package com.stonehnh.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    BAD_SQL_EXCEPTION("2000", "Lỗi liên quan đến câu lệnh sql. Vui lòng kiểm tra lại câu lệnh sql!"),
    CUSTOMER_NOT_EXISTED("2001", "Lỗi customer không tồn tại"),
    ;

    private final String code;

    private final String message;
}
