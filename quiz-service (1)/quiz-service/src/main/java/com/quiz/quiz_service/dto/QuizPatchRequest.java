package com.quiz.quiz_service.dto;


import com.quiz.quiz_service.enums.Difficulty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record QuizPatchRequest(
        @Size(min = 3, max = 100)
        String title,

        @Size(max = 500)
        String description,

        String category,

        Difficulty difficulty,

        @Min(1)
        Integer duration
) {}
