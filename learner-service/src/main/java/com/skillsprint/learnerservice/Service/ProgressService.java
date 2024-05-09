package com.skillsprint.learnerservice.Service;


import com.skillsprint.learnerservice.DTO.ProgressDTO;

import java.util.List;

public interface ProgressService {
    ProgressDTO createProgress(ProgressDTO progressDTO);
    ProgressDTO getProgressById(String id);
    ProgressDTO updateProgress(String id,int indexModule);
    void deleteProgressById(String id);
    List<ProgressDTO> getAllProgresses();
}
