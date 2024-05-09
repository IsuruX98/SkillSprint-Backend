package com.skillsprint.learnerservice.Service.ServiceImpl;

import com.skillsprint.learnerservice.DTO.ProgressDTO;
import com.skillsprint.learnerservice.DTO.ProgressMapper;
import com.skillsprint.learnerservice.Service.ProgressService;
import com.skillsprint.learnerservice.model.Progress;
import com.skillsprint.learnerservice.repository.ProgressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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
    public ProgressDTO updateProgress(String id,int indexModule) {
        ProgressDTO progressDTO = this.getProgressById(id);
        double x = progressDTO.getPercentage()+((double) 100 / progressDTO.getNoOfModules());
        if(x>100){
            log.info("Percentage can not be more than 100%");
            return null;
        }else{
            log.info(String.valueOf(x));
            progressDTO.setPercentage(x);
            Boolean[]arr =progressDTO.getIsDone();
            arr[indexModule] = true;
            progressDTO.setIsDone(arr);
            Progress progress = ProgressMapper.toEntity(progressDTO);
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
}
