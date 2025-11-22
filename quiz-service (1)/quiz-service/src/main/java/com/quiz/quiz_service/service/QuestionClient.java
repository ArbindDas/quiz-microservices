package com.quiz.quiz_service.service;

import com.quiz.quiz_service.entities.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// This line is commented out because earlier,
// you were calling the QUESTION service directly using a fixed URL (localhost:8082)
// Instead of service discovery. Example:
// @FeignClient(url = "http://localhost:8082", value = "Question-Client")
// Now you no longer need this because Eureka will handle the service location.

/**
 * This FeignClient tells Spring Boot:
 * "Call the service registered in Eureka with the name QUESTION-SERVICE"
 *
 * Eureka will automatically find the correct hostname and port.
 */
@FeignClient(name = "QUESTION-SERVICE")
public interface QuestionClient {

    /**
     * This method calls the QUESTION-SERVICE endpoint:
     * GET /api/question/quiz/{quizId}
     *
     * It returns a list of Question objects.
     *
     */
    @GetMapping("/api/question/quiz/{quizId}")
    List<Question> getQuestionOfQuiz(@PathVariable Long quizId);
}
