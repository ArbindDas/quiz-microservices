package com.quiz.quiz_service.mapper;


import com.quiz.quiz_service.dto.QuizPatchRequest;
import com.quiz.quiz_service.dto.QuizRequest;
import com.quiz.quiz_service.dto.QuizResponse;
import com.quiz.quiz_service.entities.Question;
import com.quiz.quiz_service.entities.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizMapper {

//    Quiz toEntity(QuizRequest request);
//    @Mapping(target = "questions", ignore = true) // We'll set it manually in service
//    QuizResponse toResponse(Quiz quiz);
//    // Update existing entity with new values
//    void updateQuizFromRequest(QuizRequest request, @MappingTarget Quiz quiz);
//    @org.mapstruct.BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
//    void updateQuizFromRequestPatch(QuizPatchRequest request, @MappingTarget Quiz quiz);


    Quiz toEntity(QuizRequest request);

    // Only keep this simple version
    QuizResponse toResponse(Quiz quiz);

    // Update existing entity with new values
    void updateQuizFromRequest(QuizRequest request, @MappingTarget Quiz quiz);

    @org.mapstruct.BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateQuizFromRequestPatch(QuizPatchRequest request, @MappingTarget Quiz quiz);
}


