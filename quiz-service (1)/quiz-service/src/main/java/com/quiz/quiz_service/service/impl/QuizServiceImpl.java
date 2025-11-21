package com.quiz.quiz_service.service.impl;

import com.quiz.quiz_service.dto.QuizPatchRequest;
import com.quiz.quiz_service.dto.QuizRequest;
import com.quiz.quiz_service.dto.QuizResponse;
import com.quiz.quiz_service.dto.QuizWithQuestionsResponse;
import com.quiz.quiz_service.entities.Question;
import com.quiz.quiz_service.entities.Quiz;
import com.quiz.quiz_service.exception.QuizNotFoundException;
import com.quiz.quiz_service.mapper.QuizMapper;
import com.quiz.quiz_service.repository.QuizRepository;
import com.quiz.quiz_service.service.QuestionClient;
import com.quiz.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl  implements QuizService {


    private final QuestionClient questionClient;


    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    @Autowired
    public QuizServiceImpl(QuestionClient questionClient, QuizRepository quizRepository, QuizMapper quizMapper) {
        this.questionClient = questionClient;
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
    }


    @Override
    public QuizResponse add(QuizRequest request) {

        Quiz quiz = quizMapper.toEntity(request);
        quizRepository.save(quiz);
        return quizMapper.toResponse(quiz);
    }

    @Override
    public List<QuizResponse> getAllQuizzes() {
        return  quizRepository.findAll()
                .stream()
                .map(quizMapper::toResponse)
                .toList();
    }



    @Override
    public QuizResponse getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException(id));
       return quizMapper.toResponse(quiz);
    }

    @Override
    public void deleteQuizById(Long id) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException(id));
        quizRepository.delete(quiz);
    }

    @Override
    public QuizResponse updateQuizById(Long id, QuizRequest request) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException(id));
        // Update the fields using helper method
//        updateQuizFields(quiz ,request);

        // Automatically update all fields from request to quiz
        quizMapper.updateQuizFromRequest(request, quiz);

        Quiz updatedQuiz = quizRepository.save(quiz);

        return quizMapper.toResponse(updatedQuiz);

    }

    @Override
    public QuizResponse updateQuizByIdPatch(Long id, QuizPatchRequest request) {


        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException(id));


        quizMapper.updateQuizFromRequestPatch(request , quiz);
        Quiz updatedQuiz = quizRepository.save(quiz);

        return quizMapper.toResponse(quiz);
    }


    public QuizWithQuestionsResponse getQuizWithQuestions(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException(id));

        List<Question> questions = questionClient.getQuestionOfQuiz(id);
        QuizResponse quizResponse = quizMapper.toResponse(quiz);

        return new QuizWithQuestionsResponse(quizResponse, questions);
    }



    public List<QuizWithQuestionsResponse> getAllQuizzesWithQuestions() {
        return quizRepository.findAll()
                .stream()
                .map(quiz -> {
                    List<Question> questions = questionClient.getQuestionOfQuiz(quiz.getId());
                    QuizResponse quizResponse = quizMapper.toResponse(quiz);
                    return new QuizWithQuestionsResponse(quizResponse, questions);
                })
                .collect(Collectors.toList());
    }




    private void updateQuizFields(Quiz quiz , QuizRequest request){
        quiz.setTitle(request.title());
        quiz.setDescription(request.description());
        quiz.setCategory(request.category());
        quiz.setDifficulty(request.difficulty());
        quiz.setDuration(request.duration());
    }
}
