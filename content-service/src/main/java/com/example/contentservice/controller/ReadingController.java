package com.example.contentservice.controller;

import com.example.contentservice.dto.ReadingDTO;
import com.example.contentservice.dto.ResponseDTO;
import com.example.contentservice.service.ReadingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/reading-controller")
public class ReadingController {

    @Autowired
    ReadingService readingService; // Service dependency injection


    @PostMapping("")
    public ResponseEntity<Object> addReading(@RequestBody ReadingDTO readingDTO) {
        try {
            Object result = readingService.addReading(readingDTO);
            if (result.equals("Reading Added Successfully")) {
                return ResponseEntity.status(HttpStatus.OK).body(result);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add reading");
        }
    }

    @GetMapping("{readingId}")
    // @PreAuthorize("hasAnyAuthority('admin:read', 'faculty:read', 'student:read')")
    public ResponseEntity<ReadingDTO> getReadingById(@PathVariable String readingId){
        ReadingDTO readingDTO = readingService.getReadingById(readingId);
        if(readingDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(readingDTO);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/reading-module/{moduleId}")
    public ResponseEntity<List<ReadingDTO>> getAllReadingsByModule(@PathVariable String moduleId){
        List<ReadingDTO> readingDTOList = readingService.findAllByModuleId(moduleId);

        if(readingDTOList.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(readingDTOList);
    }


    @PutMapping("")
    // @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Object> updateReadingByReadingId(@RequestBody ReadingDTO readingDTO){
        return ResponseEntity.status(HttpStatus.OK).body(readingService.updateReading(readingDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReadingById(@PathVariable String id) {
        try {
            readingService.deleteReadingById(id);
            return ResponseEntity.ok("Reading with ID " + id + " has been deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete reading with ID " + id + ": " + e.getMessage());
        }
    }



    //exception handler method
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(new ResponseDTO("Error", response)); //return response when argument exception occurred
    }



}
