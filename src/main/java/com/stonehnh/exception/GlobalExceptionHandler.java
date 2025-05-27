package com.stonehnh.exception;

import com.stonehnh.enums.ErrorCode;
import com.stonehnh.handler.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse<Object>> handleBadSqlGrammarException(BadSqlGrammarException exception){
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .success(false)
                        .data(null)
                        .message(ErrorCode.SQL_ERROR.getMessage())
                        .build()
        );
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<Object>> handleAppException(AppException ex) {
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .success(false)
                        .data(null)
                        .message(ex.getErrorCode().getMessage())
                        .build()
        );
    }
}
