//package com.quiz.quiz_service.mapper;
//
//
//import com.quiz.quiz_service.dto.QuizPatchRequest;
//import com.quiz.quiz_service.dto.QuizRequest;
//import com.quiz.quiz_service.dto.QuizResponse;
//import com.quiz.quiz_service.entities.Question;
//import com.quiz.quiz_service.entities.Quiz;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface QuizMapper {
//
//
//    Quiz toEntity(QuizRequest request);
//
//    // Only keep this simple version
//    QuizResponse toResponse(Quiz quiz);
//
//    // Update existing entity with new values
//    void updateQuizFromRequest(QuizRequest request, @MappingTarget Quiz quiz);
//
//    @org.mapstruct.BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
//    void updateQuizFromRequestPatch(QuizPatchRequest request, @MappingTarget Quiz quiz);
//}
//
//


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

// MapStruct will auto-generate the implementation class.
// componentModel = "spring" makes it available as a Spring Bean.
@Mapper(componentModel = "spring")
public interface QuizMapper {

    // -----------------------------------------------------------
    // 1. Convert QuizRequest DTO -> Quiz Entity (for creating new)
    //
    // Used in POST /quiz (create operation)
    // Takes client input (DTO) and builds a new Quiz entity
    // -----------------------------------------------------------
    Quiz toEntity(QuizRequest request);

    // -----------------------------------------------------------
    // 2. Convert Quiz Entity -> QuizResponse DTO (for returning data)
    //
    // Used in GET operations to send quiz data back to frontend
    // Makes sure only necessary fields are returned
    // -----------------------------------------------------------
    QuizResponse toResponse(Quiz quiz);

    // -----------------------------------------------------------
    // 3. FULL UPDATE (PUT)
    //
    // Copies ALL fields from QuizRequest into the existing Quiz entity
    // Even null values WILL overwrite existing fields
    // Used when client sends full updated quiz object
    // -----------------------------------------------------------
    void updateQuizFromRequest(QuizRequest request, @MappingTarget Quiz quiz);

    // -----------------------------------------------------------
    // 4. PARTIAL UPDATE (PATCH)
    //
    // Only non-null fields from QuizPatchRequest will update the Quiz entity
    // null fields are ignored → existing values stay unchanged
    // Used when client updates only specific fields
    // -----------------------------------------------------------
    @org.mapstruct.BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateQuizFromRequestPatch(QuizPatchRequest request, @MappingTarget Quiz quiz);
}
