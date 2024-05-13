package com.example.contentservice.service.impl;

import com.example.contentservice.dto.ReadingDTO;
import com.example.contentservice.model.CommonConstant;
import com.example.contentservice.model.Reading;
import com.example.contentservice.repository.ReadingRepository;
import com.example.contentservice.service.ReadingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    ReadingRepository readingRepository;

    ModelMapper mapper = new ModelMapper();
    @Override
    public Object addReading(ReadingDTO readingDTO) {
        try{

//            if(readingRepository.findCourseByCourseCodeAndStatus(courseDTO.getCourseCode(), CommonConstant.ACTIVE) != null)
//                return ResponseEntity.status(HttpStatus.FOUND).body("Course Already exist for Course Code: " + courseDTO.getCourseCode());

            Reading reading = mapper.map(readingDTO, Reading.class);
            reading.setStatus(CommonConstant.ACTIVE);

            readingRepository.save(reading);
            log.info("Reading added successfully: {}", reading);
            return "Reading Added Successfully";

        }catch(Exception e){
            log.error("Failed to add course: {}", e.getMessage());
            return "Failed to add reading";
        }
    }

    @Override
    public ReadingDTO getReadingById(String readingId) {
        try{
            Optional<Reading> reading = readingRepository.findById(readingId);
            if (reading.isPresent()) {
                return mapper.map(reading, ReadingDTO.class);
            } else
                return null;


        }catch(NoSuchElementException e){
            log.error("Reading not found for id: {}", readingId);
            throw e;
        }catch(Exception e){
            log.error("Failed to retrieve Reading {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<ReadingDTO> findAllByModuleId(String moduleId) {
        try{
            List<Reading> readings = readingRepository.findAllByModuleId(moduleId);

            if(!readings.isEmpty()){
                List<ReadingDTO> readingDTOS = new ArrayList<>();
                readings.forEach(reading -> readingDTOS.add(mapper.map(reading, ReadingDTO.class)));
                return readingDTOS;
            }
            else return new ArrayList<>();

        }catch(Exception e){
            log.error("Readings not found");
            throw e;
        }
    }



    public Object updateReading(ReadingDTO readingDTO) {
        try {
            // Fetch the existing reading from the database using its ID
            Reading existingReading = readingRepository.findById(readingDTO.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Reading not found with ID: " + readingDTO.getId()));

            // Update the attributes of the existing reading with non-null and non-empty values from the DTO
            if (readingDTO.getTitle() != null && !readingDTO.getTitle().isEmpty()) {
                existingReading.setTitle(readingDTO.getTitle());
            }
            if (readingDTO.getDescription() != null && !readingDTO.getDescription().isEmpty()) {
                existingReading.setDescription(readingDTO.getDescription());
            }
            if (readingDTO.getDuration() != null && !readingDTO.getDuration().isEmpty()) {
                existingReading.setDuration(readingDTO.getDuration());
            }
            if (readingDTO.getModuleId() != null && !readingDTO.getModuleId().isEmpty()) {
                existingReading.setModuleId(readingDTO.getModuleId());
            }

            // Save the updated reading back to the database
            readingRepository.save(existingReading);

            return existingReading;
        } catch (IllegalArgumentException ex) {
            // Rethrow the exception to be handled at the higher level
            throw ex;
        } catch (Exception e) {
            // Handle other exceptions
            log.error("Error occurred while updating reading", e);
            throw new RuntimeException("Failed to update reading: " + e.getMessage(), e);
        }
    }



    @Override
    public void deleteReadingById(String id) throws IllegalArgumentException {
        try {
            if (readingRepository.existsById(id)) { //check weather use exist
                readingRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("Reading not found with ID: " + id); //if not throw exception
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting {}", e.getMessage());
            throw new IllegalArgumentException("Failed to delete reading: " + e.getMessage(), e);
        }
    }



}
