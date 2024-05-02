package com.example.contentservice.service.impl;


import com.example.contentservice.dto.QuizDTO;
import com.example.contentservice.dto.QuizResultDTO;
import com.example.contentservice.dto.UserAnswerDTO;
import com.example.contentservice.model.Quiz;
import com.example.contentservice.repository.QuizRepo;
import com.example.contentservice.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public QuizDTO addQuiz(QuizDTO quizDTO) {
        try {
            Quiz quiz = mapper.map(quizDTO,Quiz.class);
            Quiz savedQuiz = quizRepo.save(quiz);
            return mapper.map(savedQuiz,QuizDTO.class);
        }catch (Exception e){
            log.error("Error occurred in adding Quiz : {} ",e.getMessage());
            return null;
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
    public List<QuizDTO> getAllQuizzesByModuleId(String moduleId) {
        try{
            List<Quiz> quizzes = quizRepo.findByModuleId(moduleId);     // want to check this one

            return quizzes.stream()
                    .map(quiz -> mapper.map(quiz,QuizDTO.class))
                    .collect(Collectors.toList());
        }catch (Exception e){
            log.error("Error Occurred in fetching quizzes from the moduleID");
            return null;
        }
    }

    @Override
    public QuizDTO editQuiz(String quizId, QuizDTO updatedQuizDTO) {
        try{
            Optional<Quiz> optionalQuiz = quizRepo.findById(quizId);

            if(optionalQuiz.isPresent()){
                Quiz quiz =  optionalQuiz.get();
                quiz.setModuleId(updatedQuizDTO.getModuleId());
                quiz.setQuestion(updatedQuizDTO.getQuestion());
                quiz.setOptions(updatedQuizDTO.getOptions());
                quiz.setCorrectOption(updatedQuizDTO.getCorrectOption());

                Quiz updatedQuiz = quizRepo.save(quiz);

                return mapper.map(updatedQuiz,QuizDTO.class);
            }else{
                log.info("There is no Quiz under this ID ");
                return null;
            }
        }catch (Exception e){
            log.error("Error occurred while Editing Quiz {}",e.getMessage());
            return  null;
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

    @Override
    public void deleteAllQuizzesByModuleId(String moduleId) {
        try{
            quizRepo.deleteByModuleId(moduleId);     // want to check with this
        }catch (Exception e){
            log.info("Error Occurred While quiz by ID {}",e.getMessage());
        }
    }

    @Override
    public QuizResultDTO checkAnswer(UserAnswerDTO userAnswerDTO) {
        try{
            Optional<Quiz> optionalQuiz = quizRepo.findById(userAnswerDTO.getQuizId());

            if (optionalQuiz.isPresent()){
                Quiz quiz = optionalQuiz.get();
                boolean isCorrect = (userAnswerDTO.getSelectedOption()==(quiz.getCorrectOption()));     // logic is here check if any errror

                QuizResultDTO  quizResultDTO = new QuizResultDTO();
                quizResultDTO.setQuizId(userAnswerDTO.getQuizId());
                quizResultDTO.setUserId(userAnswerDTO.getUserId());
                quizResultDTO.setStatus(isCorrect);
                quizResultDTO.setCorrectOption(quiz.getCorrectOption());

                return quizResultDTO;
            }else{
                log.info("Error in checking the answer");
                return null;
            }
        }catch (Exception e){
            log.error("Error occurred while checking answer: {}", e.getMessage());
            return null;
        }
    }
}