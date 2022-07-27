package com.zerobase.weather.controller;

import com.zerobase.weather.dto.DiaryDto;
import com.zerobase.weather.dto.DiaryResponse;
import com.zerobase.weather.service.DiaryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DiaryController {
    private final DiaryService diaryService;

    @ApiOperation("Diary 생성")
    @PostMapping("/diary")
    public DiaryResponse createDiary(@RequestParam //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                         @ApiParam(value = "yyyy-MM-dd",example = "2022-07-27") LocalDate date,
                                     @RequestBody String text) {
        diaryService.createDiary(date, text);
        return new DiaryResponse("다이어리가 성공적으로 생성되었습니다.");
    }

    @ApiOperation("Diary 조회")
    @GetMapping("/diary")
    public List<DiaryDto> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                          @ApiParam(value = "yyyy-MM-dd",example = "2022-07-27") LocalDate date) {
        return diaryService.getDiaryList(date);
    }

    @ApiOperation("특정 기간의 모든 Diary 조회")
    @GetMapping("/diaries")
    public List<DiaryDto> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                        @ApiParam(value = "yyyy-MM-dd",example = "2022-07-27") LocalDate startDate,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                    @ApiParam(value = "yyyy-MM-dd",example = "2022-07-27") LocalDate endDate) {
        return diaryService.getDiaries(startDate, endDate);
    }

    @ApiOperation("Diary 내용 변경")
    @PutMapping("/diary")
    public DiaryResponse updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                         @ApiParam(value = "yyyy-MM-dd",example = "2022-07-27") LocalDate date,
                            @RequestBody String text) {
        diaryService.updateDiary(date, text);

        return new DiaryResponse("다이어리가 성공적으로 수정되었습니다.");
    }

    @ApiOperation("Diary 삭제")
    @DeleteMapping("/diary")
    public DiaryResponse deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                         @ApiParam(value = "yyyy-MM-dd",example = "2022-07-27") LocalDate date) {
        diaryService.deleteDiary(date);

        return new DiaryResponse("다이어리가 성공적으로 삭제되었습니다.");
    }
}