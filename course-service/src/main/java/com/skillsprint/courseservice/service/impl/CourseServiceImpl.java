package com.skillsprint.courseservice.service.impl;

import com.cloudinary.Cloudinary;
import com.skillsprint.courseservice.dto.CourseDTO;
import com.skillsprint.courseservice.model.Category;
import com.skillsprint.courseservice.model.Course;
import com.skillsprint.courseservice.model.CourseWrapper;
import com.skillsprint.courseservice.repository.CategoryRepository;
import com.skillsprint.courseservice.repository.CourseRepository;
import com.skillsprint.courseservice.service.CourseService;
import com.skillsprint.courseservice.utils.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.*;

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

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Object addCourse(CourseWrapper courseWrapper) {
        try{

            if(courseRepository.findCourseById(courseWrapper.getId()) != null)
                return "Course Already exist for Course Code: " + courseWrapper.getId();

            Course course = mapper.map(courseWrapper, Course.class);
            course.setStatus(CommonConstant.PENDING);

            Map<String, String> uploadOptions = new HashMap<>();
            uploadOptions.put("resource_type","image");

            Map uploadResult = cloudinary.uploader().upload(courseWrapper.getFile().getBytes(), uploadOptions);
            String url = uploadResult.get("url").toString();

            course.setCoverImgUrl(url);

            courseRepository.save(course);
            log.info("Course added successfully: {}", course);
            return "Course Added Successfully";

        }catch(Exception e){
            log.error("Failed to add course: {}", e.getMessage());
            return "Failed to add course";
        }
    }

    //TODO - should be returned according to the given Response by Isuru ?



    @Override
    public CourseDTO getCourseById(@PathVariable String courseId) {
        try{
            Course course = courseRepository.findCourseById(courseId);
            if (course != null) {
                return mapper.map(course, CourseDTO.class);
            } else
                return null;


        }catch(NoSuchElementException e){
            log.error("Course not found for id: {}", courseId);
            throw e;
        }catch(Exception e){
            log.error("Failed to retrieve course {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Object updateCourseByCourseId(CourseDTO courseDTO) {

        try{
            Course course = courseRepository.findCourseById(courseDTO.getId());

            if(course == null)
                return "Course not found";

            if(StringUtils.hasLength(courseDTO.getCourseName()))
                course.setCourseName(courseDTO.getCourseName());
            if(StringUtils.hasLength(courseDTO.getCategoryId()))
                course.setCategoryId(courseDTO.getCategoryId());
            if(StringUtils.hasLength(courseDTO.getDescription()))
                course.setDescription(courseDTO.getDescription());
            if(courseDTO.getPrice() != null)
                course.setPrice(courseDTO.getPrice());
            if(StringUtils.hasLength(courseDTO.getLevel()))
                course.setLevel(courseDTO.getLevel());
            if(courseDTO.getSkillgained() != null)
                course.setSkillgained(courseDTO.getSkillgained());
            if(StringUtils.hasLength(courseDTO.getInstructorId()))
                course.setInstructorId(courseDTO.getInstructorId());

            courseRepository.save(course);
            log.info("Course updated successfully: {}", course);
            return "Course Updated Successfully";

        }catch(Exception e){
            log.error("Failed to update course: {}", e.getMessage());
            return "Failed to updated course";
        }
    }

    @Override
    public Object deleteCourseByCourseId(String courseId) {
        try{
            Course course = courseRepository.findCourseById(courseId);

            if(course == null)
                return "Course not found.";

            courseRepository.delete(course);
            log.info("Course Deleted successfully: {}", course);
            return "Course Deleted Successfully";

        }catch(Exception e){
            log.error("Failed to Delete course: {}", e.getMessage());
            return "Failed to Delete course";
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

    @Override
    public Object approveCourse(String courseId) {
        try{
            Optional<Course> course = courseRepository.findById(courseId);

            if(course.isPresent()){
                Course crs = course.get();
                crs.setStatus(CommonConstant.APPROVED);
                courseRepository.save(crs);
                return "Course Approved";
            }else
                return "Course not found.";
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<CourseDTO> findAllByInstructorId(String instructorId) {
        try{
            List<Course> courseList = courseRepository.findAllByInstructorId(instructorId);
            List<CourseDTO> courseDTOList = new ArrayList<>();

            if(!courseList.isEmpty()){
                courseList.forEach(course -> courseDTOList.add(mapper.map(course, CourseDTO.class)));
                return courseDTOList;
            }
            else
                throw new NullPointerException("Courses are not available for the Instructor Id: " + instructorId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<CourseDTO> getAll() {
        try{
            List<Course> courseList = courseRepository.findAll();
            List<CourseDTO> courseDTOList = new ArrayList<>();

            if(!courseList.isEmpty()){
                courseList.forEach(course -> courseDTOList.add(mapper.map(course, CourseDTO.class)));
                return courseDTOList;
            }else
                return Collections.emptyList();

        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }
}
