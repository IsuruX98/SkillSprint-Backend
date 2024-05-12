package com.example.contentservice.service.impl;


import com.example.contentservice.dto.QuizDTO;
import com.example.contentservice.dto.QuizResultDTO;
import com.example.contentservice.dto.UserAnswerDTO;
import com.example.contentservice.model.Question;
import com.example.contentservice.model.Quiz;
import com.example.contentservice.repository.QuizRepo;
import com.example.contentservice.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {


    @Autowired
     public QuizRepo quizRepo;

     ModelMapper mapper = new ModelMapper();


    @Override
    public String addQuiz(QuizDTO quizDTO) {
        try {

            Quiz avilableQuiz = quizRepo.findFirstByModuleId(quizDTO.getModuleId());

            if(avilableQuiz == null){
                Quiz quiz = mapper.map(quizDTO, Quiz.class);
                quizRepo.save(quiz);
                return "Quiz Added Successfully";
            }
            else
                return "Quiz Already Available for this module";


        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public QuizDTO getQuizById(String quizId) {
        try{
            Optional<Quiz> optionalQuiz = quizRepo.findById(quizId);
            if(optionalQuiz.isPresent()){
                return mapper.map(optionalQuiz.get(),QuizDTO.class);
            }else{
                log.error("There is no Quiz from this ID");
                return null;
            }
        }catch (Exception e){
            log.error("Error Occurred in fetching single Quiz {}",e.getMessage());
            return null;
        }
    }

    @Override
    public QuizDTO getQuizByModuleId(String moduleId) {
        try{
            Quiz quiz = quizRepo.findFirstByModuleId(moduleId);

            if(quiz != null)
                return mapper.map(quiz, QuizDTO.class);
            else
                return null;


        }catch (Exception e){
            log.error("Error Occurred in fetching quizzes from the moduleID");
            throw e;
        }
    }

    @Override
    public QuizDTO editQuiz(String quizId, QuizDTO updatedQuizDTO) {
        try {
            Optional<Quiz> optionalQuiz = quizRepo.findById(quizId);

            if (optionalQuiz.isPresent()) {
                Quiz quiz = optionalQuiz.get();
                quiz.setModuleId(updatedQuizDTO.getModuleId());
                quiz.setDescription(updatedQuizDTO.getDescription());
                quiz.setTitle(updatedQuizDTO.getTitle());

                if (updatedQuizDTO.getQuestions() != null && updatedQuizDTO.getQuestions().length > 0) {
                    Question[] questionsArray = updatedQuizDTO.getQuestions();
                    quiz.setQuestions(questionsArray);
                } else {
                    quiz.setQuestions(new Question[0]);
                }

                // Set the correct answers array
                quiz.setCorrectAnswers(updatedQuizDTO.getCorrectAnswers());

                // Save the updated quiz
                Quiz updatedQuiz = quizRepo.save(quiz);
                return mapper.map(updatedQuiz, QuizDTO.class);
            } else {
                log.info("There is no Quiz under this ID");
                return null;
            }
        } catch (Exception e) {
            log.error("Error occurred while Editing Quiz {}", e.getMessage());
            return null;
        }
    }





    @Override
    public void deleteQuizById(String quizId) {
        try{
            quizRepo.deleteById(quizId);
        }catch (Exception e){
            log.error("Error occurred while deleting {}",e.getMessage());
        }
    }
//
//    @Override
//    public void deleteAllQuizzesByModuleId(String moduleId) {
//        try{
//            quizRepo.deleteByModuleId(moduleId);     // want to check with this
//        }catch (Exception e){
//            log.info("Error Occurred While quiz by ID {}",e.getMessage());
//        }
//    }
//
//    @Override
//    public QuizResultDTO checkAnswer(UserAnswerDTO userAnswerDTO) {
//        try{
//            Optional<Quiz> optionalQuiz = quizRepo.findById(userAnswerDTO.getQuizId());
//
//            if (optionalQuiz.isPresent()){
//                Quiz quiz = optionalQuiz.get();
//                boolean isCorrect = (userAnswerDTO.getSelectedOption()==(quiz.getCorrectOption()));     // logic is here check if any errror
//
//                QuizResultDTO  quizResultDTO = new QuizResultDTO();
//                quizResultDTO.setQuizId(userAnswerDTO.getQuizId());
//                quizResultDTO.setUserId(userAnswerDTO.getUserId());
//                quizResultDTO.setStatus(isCorrect);
//                quizResultDTO.setCorrectOption(quiz.getCorrectOption());
//
//                return quizResultDTO;
//            }else{
//                log.info("Error in checking the answer");
//                return null;
//            }
//        }catch (Exception e){
//            log.error("Error occurred while checking answer: {}", e.getMessage());
//            return null;
//        }
//    }
}
