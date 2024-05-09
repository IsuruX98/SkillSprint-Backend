package com.skillsprint.learnerservice.DTO;

import com.skillsprint.learnerservice.model.Progress;

import java.util.ArrayList;
import java.util.List;

public interface ProgressMapper {
    static ProgressDTO toDTO(Progress progress) {
        ProgressDTO dto = new ProgressDTO();
        dto.setId(progress.getId());
        dto.setUserId(progress.getUserId());
        dto.setCourseId(progress.getCourseId());
        dto.setNoOfModules(progress.getNoOfModules());
        dto.setPercentage(progress.getPercentage());
        dto.setIsDone(progress.getIsDone());
        return dto;
    }

    static Progress toEntity(ProgressDTO progressDTO) {
        Progress entity = new Progress();
        entity.setId(progressDTO.getId());
        entity.setUserId(progressDTO.getUserId());
        entity.setCourseId(progressDTO.getCourseId());
        entity.setNoOfModules(progressDTO.getNoOfModules());
        entity.setPercentage(progressDTO.getPercentage());
        entity.setIsDone(progressDTO.getIsDone());
        return entity;
    }

    static List<ProgressDTO> toDTOList(List<Progress> progresses) {
        List<ProgressDTO> dtos = new ArrayList<>();
        for (Progress progress : progresses) {
            dtos.add(toDTO(progress));
        }
        return dtos;
    }
}
