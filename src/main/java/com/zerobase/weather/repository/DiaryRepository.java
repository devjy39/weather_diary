package com.zerobase.weather.repository;

import com.zerobase.weather.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByDate(LocalDate date);

    List<Diary> findAllByDateBetween(LocalDate start, LocalDate end);

    Optional<Diary> findFirstByDate(LocalDate date);

    @Transactional
    void deleteAllByDate(LocalDate date);
}
