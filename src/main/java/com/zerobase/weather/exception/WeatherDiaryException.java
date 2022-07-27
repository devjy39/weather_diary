package com.zerobase.weather.exception;

import lombok.Getter;

@Getter
public class WeatherDiaryException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public WeatherDiaryException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getMessage();
    }
}
