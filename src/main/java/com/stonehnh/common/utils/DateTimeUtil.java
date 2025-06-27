package com.stonehnh.common.utils;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateTimeUtil {

    private static final String SQL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Trả về ngày giờ hiện tại dưới dạng chuỗi format yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTimeString() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(SQL_DATE_TIME_PATTERN);
        return sdf.format(now);
    }

    /**
     * Chuyển một java.util.Date thành chuỗi format yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("date cannot be null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(SQL_DATE_TIME_PATTERN);
        return sdf.format(date);
    }
}
