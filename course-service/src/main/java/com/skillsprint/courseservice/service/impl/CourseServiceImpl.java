package com.skillsprint.courseservice.service.impl;

import com.skillsprint.courseservice.dto.CourseDTO;
import com.skillsprint.courseservice.model.Category;
import com.skillsprint.courseservice.model.Course;
import com.skillsprint.courseservice.repository.CategoryRepository;
import com.skillsprint.courseservice.repository.CourseRepository;
import com.skillsprint.courseservice.service.CourseService;
import com.skillsprint.courseservice.utils.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CategoryRepository categoryRepository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public Object addCourse(CourseDTO courseDTO) {
        try{

            if(courseRepository.findCourseByCourseCodeAndStatus(courseDTO.getCourseCode(), CommonConstant.ACTIVE) != null)
                return ResponseEntity.status(HttpStatus.FOUND).body("Course Already exist for Course Code: " + courseDTO.getCourseCode());

            Course course = mapper.map(courseDTO, Course.class);
            course.setStatus(CommonConstant.ACTIVE);

            courseRepository.save(course);
            log.info("Course added successfully: {}", course);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course Added Successfully");

        }catch(Exception e){
            log.error("Failed to add course: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add course");
        }
    }


    @Override
    public CourseDTO getCourseByCourseCode(@PathVariable String courseCode) {
        try{
            Course course = courseRepository.findCourseByCourseCodeAndStatus(courseCode, CommonConstant.ACTIVE);
            if (course != null) {
                return mapper.map(course, CourseDTO.class);
            } else {
                return null;
            }

        }catch(NoSuchElementException e){
            log.error("Course not found for id: {}", courseCode);
            throw e;
        }catch(Exception e){
            log.error("Failed to retrieve course {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Object updateCourseByCourseCode(String courseCode, CourseDTO courseDTO) {

        try{
            Course course = courseRepository.findCourseByCourseCodeAndStatus(courseCode, CommonConstant.ACTIVE);

            if(course == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");

            if(StringUtils.hasLength(courseDTO.getCourseCode()))
                course.setCourseCode(courseDTO.getCourseCode());
            if(StringUtils.hasLength(courseDTO.getCourseName()))
                course.setCourseName(courseDTO.getCourseName());
            if(StringUtils.hasLength(courseDTO.getCategoryId()))
                course.setCategoryId(courseDTO.getCategoryId());
            if(courseDTO.getPrice() != null)
                course.setPrice(courseDTO.getPrice());
            if(StringUtils.hasLength(courseDTO.getStatus()))
                course.setStatus(courseDTO.getStatus());


            courseRepository.save(course);
            log.info("Course updated successfully: {}", course);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course Updated Successfully");

        }catch(Exception e){
            log.error("Failed to update course: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to updated course");
        }
    }

    @Override
    public Object deleteCourseByCourseCode(String courseCode) {
        try{
            Course course = courseRepository.findCourseByCourseCodeAndStatus(courseCode, CommonConstant.ACTIVE);
            course.setStatus(CommonConstant.DEACTIVE);

            courseRepository.save(course);
            log.info("Course Deleted successfully: {}", course);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course Deleted Successfully");

        }catch(Exception e){
            log.error("Failed to Delete course: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Delete course");
        }
    }

    @Override
    public List<CourseDTO> getCoursesByCategoryCode(String categoryCode) {
        try{
            Category category = categoryRepository.findByCategoryCode(categoryCode);
            if(category != null){
                List<Course> courses = courseRepository.findAllByCategoryId(category.getId().toString());

                if (!courses.isEmpty()) {
                    List<CourseDTO> courseDTOS = new ArrayList<>();

                    courses.forEach(course -> courseDTOS.add(mapper.map(course, CourseDTO.class)));
                    return courseDTOS;
                }

                else {
                    log.error("Course not Available");
                    throw new NoSuchElementException("Course not found");
                }
            }
            else
                throw new NoSuchElementException("Category not found");

        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }
}
