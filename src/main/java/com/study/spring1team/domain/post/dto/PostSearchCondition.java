package com.study.spring1team.domain.post.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public record PostSearchCondition(
        String keyword,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate startDate,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate endDate
) {
}