package com.stonehnh.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    BAD_SQL_EXCEPTION("2000", "Lỗi liên quan đến câu lệnh sql. Vui lòng kiểm tra lại câu lệnh sql!"),
    CUSTOMER_NOT_EXISTED("2001", "Lỗi customer không tồn tại"),
    HOMESTAY_NOT_FOUND("2005", "Lỗi homestay không tồn tại"),
    IMAGE_NOT_FOUND("2006", "Lỗi homestayImage không tồn tại"),
    RULE_NOT_FOUND("2007", "Lỗi rule không tồn tại"),
    OWNER_NOT_FOUND("2008", "Lỗi owner không tồn tại"),
    PAYMENT_NOT_EXISTED("2009", "Lỗi payment không tồn tại"),
    ;

    private final String code;

    private final String message;
}
