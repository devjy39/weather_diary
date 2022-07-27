package com.zerobase.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.util.List;
import com.zerobase.weather.dto.DiaryDto;
import com.zerobase.weather.service.DiaryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DiaryController.class)
class DiaryControllerTest {
    @MockBean
    private DiaryService diaryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("특정 일자 모든 diary 조회")
    void readDiaryTest() throws Exception {
        //given
        given(diaryService.getDiaryList(any()))
                .willReturn(new ArrayList<>(List.of(DiaryDto.builder()
                        .weather("cloudy")
                        .build(),DiaryDto.builder()
                        .weather("rainy")
                        .build())));
        //when
        //then
        mockMvc.perform(get("/diary").param("date","2022-07-27"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("2"))
                .andExpect(jsonPath("$[0].weather").value("cloudy"))
                .andExpect(jsonPath("$[1].weather").value("rainy"));
    }

    @Test
    @DisplayName("특정 기간 모든 diary 조회")
    void readDiariesTest() throws Exception {
        //given
        given(diaryService.getDiaries(any(),any()))
                .willReturn(new ArrayList<>(List.of(DiaryDto.builder()
                        .weather("cloudy")
                        .build(),DiaryDto.builder()
                        .weather("rainy")
                        .build())));
        //when
        //then
        mockMvc.perform(get("/diaries").param("startDate", "2022-01-01").param("endDate", "2022-07-27"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("2"))
                .andExpect(jsonPath("$[0].weather").value("cloudy"))
                .andExpect(jsonPath("$[1].weather").value("rainy"));
    }

    @Test
    @DisplayName("diary 생성")
    void createDiaryTest() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/diary").param("date", "2022-07-27").content("오늘의 일기 내용"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("diary 내용 변경")
    void updateDiaryTest() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(put("/diary").param("date", "2022-07-27").content("오늘의 일기 내용"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("diary 삭제")
    void deleteDiaryTest() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(delete("/diary").param("date", "2022-07-27"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}