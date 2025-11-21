package com.quiz.quiz_service.service;

import com.quiz.quiz_service.dto.QuizPatchRequest;
import com.quiz.quiz_service.dto.QuizRequest;
import com.quiz.quiz_service.dto.QuizResponse;
import com.quiz.quiz_service.dto.QuizWithQuestionsResponse;
import com.quiz.quiz_service.entities.Quiz;

import java.util.List;

public interface QuizService {

    QuizResponse add(QuizRequest request);
    List<QuizResponse> getAllQuizzes();
    QuizResponse getQuizById(Long id);
    void deleteQuizById(Long id);
    QuizResponse updateQuizById(Long id , QuizRequest request);

    QuizResponse updateQuizByIdPatch(Long id, QuizPatchRequest request);

    // New methods with questions
    QuizWithQuestionsResponse getQuizWithQuestions(Long id);
    List<QuizWithQuestionsResponse> getAllQuizzesWithQuestions();
}
