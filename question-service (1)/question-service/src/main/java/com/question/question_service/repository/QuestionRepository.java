package com.question.question_service.repository;

import com.question.question_service.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Question , Long> {


    List<Question>findByQuizId(Long id);
}
