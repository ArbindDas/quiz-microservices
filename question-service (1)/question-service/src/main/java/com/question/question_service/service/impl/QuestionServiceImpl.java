package com.question.question_service.service.impl;

import com.question.question_service.entities.Question;
import com.question.question_service.repository.QuestionRepository;
import com.question.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionServiceImpl implements QuestionService {


    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;

    }

    @Override
    public Question add(Question question) {
      return questionRepository.save(question);
    }

    @Override
    public Question get(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id : " + id));
    }

    @Override
    public List<Question> getAllQuestion() {
      return   questionRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getQuestionOfQuiz(Long id) {
        return questionRepository.findByQuizId(id);
    }



}
