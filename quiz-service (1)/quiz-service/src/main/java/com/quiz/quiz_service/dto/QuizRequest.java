package com.quiz.quiz_service.dto;

import com.quiz.quiz_service.enums.Difficulty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QuizRequest(
        @NotBlank
        @Size(min = 3 , max = 100)
        String title,

        @Size(max = 500)
        String description,

        @NotBlank
        String category,

        @NotNull
        Difficulty difficulty,

        @Min(1)
        Integer duration

) {}
