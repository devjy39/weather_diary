package com.zerobase.weather.dto;

import com.zerobase.weather.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final ErrorCode errorCode;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
