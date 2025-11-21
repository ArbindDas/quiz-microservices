package com.quiz.quiz_service.controller;

import com.quiz.quiz_service.dto.QuizPatchRequest;
import com.quiz.quiz_service.dto.QuizRequest;
import com.quiz.quiz_service.dto.QuizResponse;
import com.quiz.quiz_service.dto.QuizWithQuestionsResponse;
import com.quiz.quiz_service.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // Create a new quiz
    @PostMapping
    public ResponseEntity<QuizResponse> addQuiz(@RequestBody QuizRequest request) {
        logger.info("Received request to create new quiz with title: {}", request.title());
        logger.debug("Quiz request details - Category: {}, Difficulty: {}, Duration: {}",
                request.category(), request.difficulty(), request.duration());

        QuizResponse quizResponse = quizService.add(request);

        logger.info("Successfully created quiz with ID: {}", quizResponse.id());
        return new ResponseEntity<>(quizResponse, HttpStatus.CREATED);
    }

    // Get all quizzes
    @GetMapping
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {
        logger.info("Received request to get all quizzes");

        List<QuizResponse> quizzes = quizService.getAllQuizzes();

        logger.info("Returning {} quizzes", quizzes.size());
        return ResponseEntity.ok(quizzes);
    }

    // Get quiz by ID
    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable Long id) {
        logger.info("Received request to get quiz by ID: {}", id);

        QuizResponse quizResponse = quizService.getQuizById(id);

        logger.info("Successfully retrieved quiz with ID: {}", id);
        return ResponseEntity.ok(quizResponse);
    }

    // Update quiz by ID
    @PutMapping("/{id}")
    public ResponseEntity<QuizResponse> updateQuiz(@PathVariable Long id,
                                                   @RequestBody QuizRequest request) {
        logger.info("Received request to update quiz with ID: {}", id);
        logger.debug("Update details - Title: {}, Category: {}",
                request.title(), request.category());

        QuizResponse updatedQuiz = quizService.updateQuizById(id, request);

        logger.info("Successfully updated quiz with ID: {}", id);
        return ResponseEntity.ok(updatedQuiz);
    }

    // Delete quiz by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        logger.info("Received request to delete quiz with ID: {}", id);

        quizService.deleteQuizById(id);

        logger.info("Successfully deleted quiz with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuizResponse> updateQuiz(
            @PathVariable Long id,
            @RequestBody QuizPatchRequest request) {

        QuizResponse response = quizService.updateQuizByIdPatch(id, request);
        return ResponseEntity.ok(response);
    }


    // NEW ENDPOINTS WITH QUESTIONS

    @GetMapping("/{id}/with-questions")
    public ResponseEntity<QuizWithQuestionsResponse> getQuizWithQuestions(@PathVariable Long id) {
        QuizWithQuestionsResponse response = quizService.getQuizWithQuestions(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/with-questions")
    public ResponseEntity<List<QuizWithQuestionsResponse>> getAllQuizzesWithQuestions() {
        List<QuizWithQuestionsResponse> responses = quizService.getAllQuizzesWithQuestions();
        return ResponseEntity.ok(responses);
    }





    @GetMapping("/test")
    public Map<String , String>test(){
        logger.info("Successfully the test endpoints working.....");
        return Map.of("message", "jai shree ram");
    }
}