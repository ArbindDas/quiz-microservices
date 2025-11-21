package com.question.question_service.service;


import com.question.question_service.entities.Question;

import java.util.List;

public interface QuestionService {



    Question add(Question question);
    Question get(Long id);
    List<Question>getAllQuestion();

    void deleteById(Long id);


    List<Question>getQuestionOfQuiz(Long id);

}
