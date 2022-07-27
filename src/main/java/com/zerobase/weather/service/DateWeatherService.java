package com.zerobase.weather.service;

import com.zerobase.weather.WeatherDiaryApplication;
import com.zerobase.weather.domain.DateWeather;
import com.zerobase.weather.repository.DateWeatherRepository;
import com.zerobase.weather.util.OpenWeatherApi;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class DateWeatherService {
    private final DateWeatherRepository dateWeatherRepository;
    private final OpenWeatherApi openWeatherApi;
    private static final Logger logger = LoggerFactory.getLogger(WeatherDiaryApplication.class);

    @Transactional
    @Scheduled(cron = "0 0 1 * * *") // 매일 1시
    public void saveWeatherDate() {
        Map<String, Object> weatherMap = openWeatherApi.getNowWeatherMap();

        dateWeatherRepository.save(DateWeather.builder()
                .date(LocalDate.now())
                .weather(weatherMap.get("main").toString())
                .icon(weatherMap.get("icon").toString())
                .temperature((Double) weatherMap.get("temp"))
                .build());

        logger.debug("open weather api data saved.");
    }

}
