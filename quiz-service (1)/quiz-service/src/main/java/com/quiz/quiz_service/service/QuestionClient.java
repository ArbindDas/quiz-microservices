package com.quiz.quiz_service.service;


import com.quiz.quiz_service.entities.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8082", value = "Question-Client")
public interface QuestionClient {


    @GetMapping("/api/question/quiz/{quizId}")
        // Added /api
    List<Question> getQuestionOfQuiz(@PathVariable Long quizId);


}
