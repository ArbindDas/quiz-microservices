package com.quiz.quiz_service.dto;

import com.quiz.quiz_service.entities.Question;

import java.util.List;

public record QuizWithQuestionsResponse(
        QuizResponse quiz,
        List<Question> questions
) {
}
