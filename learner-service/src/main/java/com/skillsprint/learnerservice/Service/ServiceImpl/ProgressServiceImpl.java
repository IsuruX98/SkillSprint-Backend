package com.skillsprint.learnerservice.Service.ServiceImpl;

import com.skillsprint.learnerservice.dto.ProgressDTO;
import com.skillsprint.learnerservice.dto.ProgressMapper;
import com.skillsprint.learnerservice.Service.ProgressService;
import com.skillsprint.learnerservice.model.Progress;
import com.skillsprint.learnerservice.repository.ProgressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class ProgressServiceImpl implements ProgressService {
    private final ProgressRepository progressRepository;
    private double increment;

    @Autowired
    public ProgressServiceImpl(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @Override
    public ProgressDTO createProgress(ProgressDTO progressDTO) {

        Optional<Object> existingProgressOptional = progressRepository.findByUserIdAndCourseId(progressDTO.getUserId(), progressDTO.getCourseId());
        if(existingProgressOptional.isPresent()){
            throw new RuntimeException("Progress already exists same user and course ");
        }
        Random random = new Random();
        progressDTO.setId(String.valueOf(random.nextInt(Integer.MAX_VALUE)));
        this.increment = (double) 100 / progressDTO.getNoOfModules();
        double percentage = 0;
        Boolean[] isDone = new Boolean[progressDTO.getNoOfModules()];
        Arrays.fill(isDone, false);

        progressDTO.setPercentage(percentage);
        progressDTO.setIsDone(isDone);

        Progress progress = ProgressMapper.toEntity(progressDTO);
        progressRepository.save(progress);
        return progressDTO;
    }

    @Override
    public ProgressDTO getProgressById(String id) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found with id: " + id));
        return ProgressMapper.toDTO(progress);
    }

    @Override
    public ProgressDTO updateProgress(String userId, String courseId, int indexModule) {
        Progress progress = (Progress) progressRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new RuntimeException("Progress not found with userId: " + userId + " and courseId: " + courseId));

        ProgressDTO progressDTO = ProgressMapper.toDTO(progress);
        double x = progressDTO.getPercentage() + ((double) 100 / progressDTO.getNoOfModules());

        if (x > 100) {
            log.info("Percentage cannot be more than 100%");
            return null;
        } else {
            log.info(String.valueOf(x));
            progressDTO.setPercentage(x);
            Boolean[] arr = progressDTO.getIsDone();
            arr[indexModule] = true;
            progressDTO.setIsDone(arr);

            // Update the Progress entity with the modified data
            progress = ProgressMapper.toEntity(progressDTO);
            progressRepository.save(progress);

            return progressDTO;
        }
    }


    @Override
    public void deleteProgressById(String id) {
        progressRepository.deleteById(id);
    }

    @Override
    public List<ProgressDTO> getAllProgresses() {
        List<Progress> progresses = progressRepository.findAll();
        return ProgressMapper.toDTOList(progresses);
    }

    @Override
    public ProgressDTO getProgressByUserIdAndCourseId(String userId, String courseId) {
        Progress progress = (Progress) progressRepository.findByUserIdAndCourseId(userId, courseId)
                .orElse(null);
        if (progress != null) {
            return ProgressMapper.toDTO(progress);
        } else {
            return null;
        }
    }

}
