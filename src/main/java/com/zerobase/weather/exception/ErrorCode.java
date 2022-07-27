package com.zerobase.weather.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    NOT_FOUND_WEATHER_DATA(" 날씨 정보가 존재하지 않습니다."),
    NOT_FOUND_DIARY("해당 일자 일기가 없습니다."),
    INVALID_DATE("날짜 형식이 올바르지 않거나, 유효하지 않은 날짜입니다."),
    ;

    private final String message;
}
