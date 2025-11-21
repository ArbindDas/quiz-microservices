package com.quiz.quiz_service.repository;

import com.quiz.quiz_service.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizRepository extends JpaRepository<Quiz , Long> {


}
