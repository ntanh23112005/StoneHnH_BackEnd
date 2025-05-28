package com.stonehnh.common.exception;

import com.stonehnh.common.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppException extends RuntimeException {
    private ErrorCode errorCode;
}
