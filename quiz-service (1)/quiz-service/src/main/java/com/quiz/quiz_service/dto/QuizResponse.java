package com.quiz.quiz_service.dto;

import com.quiz.quiz_service.enums.Difficulty;

import java.time.LocalDateTime;

public record QuizResponse(
        Long id,
        String title,
        String description,
        String category,
        Difficulty difficulty,
        Integer duration,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
        ) {

}
