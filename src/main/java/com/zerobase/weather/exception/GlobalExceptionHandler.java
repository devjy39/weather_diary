package com.zerobase.weather.exception;

import com.zerobase.weather.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WeatherDiaryException.class)
    public ErrorResponse weatherDiaryExceptionHandler(WeatherDiaryException e) {
        log.error(e.getErrorMessage());

        return new ErrorResponse(e.getErrorCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        log.error(e.getErrorCode() + " : " + e.getMessage());

        return new ErrorResponse(ErrorCode.INVALID_DATE);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse exceptionHandler(Exception e) {
        log.error(e.getMessage());

        return new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
