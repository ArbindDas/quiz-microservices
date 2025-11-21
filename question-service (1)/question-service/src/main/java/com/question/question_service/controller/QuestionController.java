package com.question.question_service.controller;


import com.question.question_service.entities.Question;
import com.question.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

   @PostMapping
    public ResponseEntity<?>createQuestion(@RequestBody Question question){
       Question add = questionService.add(question);
       return new ResponseEntity<>(add , HttpStatus.CREATED);
   }

   @GetMapping
    public ResponseEntity<?>getALlQuestion(){

       List<Question> allQuestion = questionService.getAllQuestion();

       return new ResponseEntity<>(allQuestion, HttpStatus.OK);
   }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id){
        Question question = questionService.get(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteById(@PathVariable Long id){
        questionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }



   @GetMapping("/quiz/{id}")
    public ResponseEntity<?>getQuestionsOfQuiz(@PathVariable  Long id){
       List<Question> questionOfTheQuiz = questionService.getQuestionOfQuiz(id);
       return new ResponseEntity<>(questionOfTheQuiz , HttpStatus.OK);
   }

}
