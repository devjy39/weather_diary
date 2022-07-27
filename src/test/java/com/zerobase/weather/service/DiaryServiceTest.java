package com.zerobase.weather.service;

import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.dto.DiaryDto;
import com.zerobase.weather.exception.ErrorCode;
import com.zerobase.weather.exception.WeatherDiaryException;
import com.zerobase.weather.repository.DateWeatherRepository;
import com.zerobase.weather.repository.DiaryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DiaryServiceTest {
    @Mock
    private DiaryRepository diaryRepository;
    
    @Mock
    private DateWeatherRepository dateWeatherRepository;
    
    @InjectMocks
    private DiaryService diaryService;

    @Test
    @DisplayName("다이어리 생성 시 없는 날씨 데이터 에러")
    void createDiary() {
        //given
        given(dateWeatherRepository.findByDate(any()))
                .willReturn(Optional.empty());
        //when
        WeatherDiaryException exception = assertThrows(WeatherDiaryException.class, 
                () -> diaryService.createDiary(LocalDate.now(), "오늘의 일기"));
        //then
        assertEquals(ErrorCode.NOT_FOUND_WEATHER_DATA, exception.getErrorCode());
    }
    
    @Test
    @DisplayName("다이어리 list get 성공")
    void getDiaryList() {
        //given
        given(diaryRepository.findAllByDate(any()))
                .willReturn(new ArrayList<>(List.of(Diary.builder().weather("cloudy").build())));
        //when
        List<DiaryDto> diaryList = diaryService.getDiaryList(LocalDate.now());
        //then
        assertEquals("cloudy", diaryList.get(0).getWeather());
    }

    @Test
    @DisplayName("기간의 모든 다이어리 list get 성공")
    void getDiaries() {
        //given
        given(diaryRepository.findAllByDateBetween(any(),any()))
                .willReturn(new ArrayList<>(List.of(Diary.builder().weather("cloudy").build())));
        //when
        List<DiaryDto> diaryList = diaryService.getDiaries(LocalDate.now(),LocalDate.now().plusMonths(1));
        //then
        System.out.println(diaryList);
        assertEquals("cloudy", diaryList.get(0).getWeather());
    }

    @Test
    @DisplayName("다이어리 업데이트 실패")
    void updateDiary() {
        //given
        given(diaryRepository.findFirstByDate(any()))
                .willReturn(Optional.empty());
        //when
        WeatherDiaryException exception = assertThrows(WeatherDiaryException.class,
                () -> diaryService.updateDiary(LocalDate.now(), "오늘의 일기"));
        //then
        assertEquals(ErrorCode.NOT_FOUND_DIARY, exception.getErrorCode());
    }

}