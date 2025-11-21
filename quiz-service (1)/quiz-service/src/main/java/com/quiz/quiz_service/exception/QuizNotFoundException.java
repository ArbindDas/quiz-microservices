package com.quiz.quiz_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class QuizNotFoundException  extends RuntimeException{

    private Long quizId;

    public QuizNotFoundException(String message) {
        super(message);
    }

    public QuizNotFoundException(Long quizId){
        super("Quiz not found with id : "+ quizId);
        this.quizId = quizId;
    }

}
