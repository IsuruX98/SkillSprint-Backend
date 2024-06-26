package com.skillsprint.courseservice.service.impl;

import com.cloudinary.Cloudinary;
import com.skillsprint.courseservice.dto.*;
import com.skillsprint.courseservice.feign.*;
import com.skillsprint.courseservice.model.Category;
import com.skillsprint.courseservice.model.Course;
import com.skillsprint.courseservice.model.CourseWrapper;
import com.skillsprint.courseservice.model.Module;
import com.skillsprint.courseservice.repository.CategoryRepository;
import com.skillsprint.courseservice.repository.CourseRepository;
import com.skillsprint.courseservice.repository.ModuleRepository;
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
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    CategoryRepository categoryRepository;

    ModelMapper mapper = new ModelMapper();

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    INotification iNotification;

    @Autowired
    IUser iUser;

    @Autowired
    IContent iContent;

    @Autowired
    EmailBodyDTO emailBodyDTO;
    @Autowired
    MessageDTO messageDTO;
    @Autowired
    UserDTO userDTO;


    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Override
    public Object addCourse(CourseWrapper courseWrapper) {
        try{

            if(courseRepository.findCourseById(courseWrapper.getId()) != null)
                return "Course Already exist for Course Code: " + courseWrapper.getId();

            Course course = mapper.map(courseWrapper, Course.class);
            course.setStatus(CommonConstant.PENDING);

            //cloudinary coverImage Url
            String url = cloudinaryUrl(courseWrapper);

            course.setCoverImgUrl(url);

            courseRepository.save(course);
            log.info("Course added successfully: {}", course);
            return "Course Added Successfully";

        }catch(Exception e){
            log.error("Failed to add course: {}", e.getMessage());
            return "Failed to add course";
        }
    }



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
    public String updateCourseByCourseId(CourseWrapper courseWrapper) {

        try{
            Course course = courseRepository.findCourseById(courseWrapper.getId());

            if(course == null)
                return "Course not found";

            if(StringUtils.hasLength(courseWrapper.getCourseName()))
                course.setCourseName(courseWrapper.getCourseName());
            if(StringUtils.hasLength(courseWrapper.getCategoryId()))
                course.setCategoryId(courseWrapper.getCategoryId());
            if(StringUtils.hasLength(courseWrapper.getDescription()))
                course.setDescription(courseWrapper.getDescription());
            if(courseWrapper.getPrice() != null)
                course.setPrice(courseWrapper.getPrice());
            if(StringUtils.hasLength(courseWrapper.getLevel()))
                course.setLevel(courseWrapper.getLevel());
            if(courseWrapper.getSkillgained() != null)
                course.setSkillgained(courseWrapper.getSkillgained());
            if(StringUtils.hasLength(courseWrapper.getInstructorId()))
                course.setInstructorId(courseWrapper.getInstructorId());
            if(!courseWrapper.getFile().isEmpty()){
                course.setCoverImgUrl(cloudinaryUrl(courseWrapper));
            }

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
    public Object approveCourse(String courseId,String userEmail) {
        try{
            Optional<Course> course = courseRepository.findById(courseId);

            if(course.isPresent()){

                Course crs = course.get();
                 userDTO=iUser.getUserDTOById(crs.getInstructorId());

                crs.setStatus(CommonConstant.APPROVED);
                courseRepository.save(crs);

                emailBodyDTO.setTo(userDTO.getEmail());
                emailBodyDTO.setMsg("Dear " + userDTO.getUserName() + ",\n\n" +
                        "Congratulations! Your "+crs.getCourseName()+ " Course successfully added to the system."+"\n\n" +
                        "Thank you for choosing SkillSprint.\n\n" +
                        "Best regards,\n" +
                        "SkillSprint Team");

                emailBodyDTO.setSubject("SkillSprint Course Enrollment");

                messageDTO.setNumber(userDTO.getContactNo());
                messageDTO.setMessageBody("Dear " + userDTO.getUserName() + ",\n\n" +
                        " \" Your "+crs.getCourseName()+"Course successfully added to the system.\n\n" +
                        "Best regards,\n" +
                        "SkillSprint Team");

                executorService.submit(() -> iNotification.sendEmail(emailBodyDTO)); // Submit email sending task to executor service
                executorService.submit(() -> iNotification.sendSms(messageDTO)); // Submit SMS sending task to executor service

                return "Course Approved";
            }else
                return "Course not found.";
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Object declineCourse(String courseId,String userEmail) {
        try{
            Optional<Course> course = courseRepository.findById(courseId);

            if(course.isPresent()){

                Course crs = course.get();
                userDTO=iUser.getUserDTOById(crs.getInstructorId());

                crs.setStatus(CommonConstant.DECLINED);
                courseRepository.save(crs);

                emailBodyDTO.setTo(userDTO.getEmail());
                emailBodyDTO.setMsg("Dear " + userDTO.getUserName() + ",\n\n" +
                        "Your "+crs.getCourseName()+ " Course declined by admin."+"\n\n" +
                        "Thank you for choosing SkillSprint.\n\n" +
                        "Best regards,\n" +
                        "SkillSprint Team");

                emailBodyDTO.setSubject("SkillSprint Course Enrollment");

                messageDTO.setNumber(userDTO.getContactNo());
                messageDTO.setMessageBody("Dear " + userDTO.getUserName() + ",\n\n" +
                        " \" Your "+crs.getCourseName()+" Course declined by admin.\n\n" +
                        "Best regards,\n" +
                        "SkillSprint Team");

                executorService.submit(() -> iNotification.sendEmail(emailBodyDTO)); // Submit email sending task to executor service
                executorService.submit(() -> iNotification.sendSms(messageDTO)); // Submit SMS sending task to executor service

                return "Course Declined";
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


    @Override
    public List<CourseDTO> getAllAproved() {
        try{
            List<Course> courseList = courseRepository.findAllByStatus(CommonConstant.APPROVED);
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
    @Override
    public DetailedCourseDTO getAllDetailedCourses(String courseId) {
        try{
                Course course = courseRepository.findCourseById(courseId);

                if(course != null){
                    DetailedCourseDTO detailedCourseDTO = new DetailedCourseDTO();

                    detailedCourseDTO.setId(course.getId());
                    detailedCourseDTO.setCourseName(course.getCourseName());
                    detailedCourseDTO.setCategoryId(course.getCategoryId());
                    detailedCourseDTO.setDescription(course.getDescription());
                    detailedCourseDTO.setPrice(course.getPrice());
                    detailedCourseDTO.setLevel(course.getLevel());
                    detailedCourseDTO.setSkillgained(course.getSkillgained());
                    detailedCourseDTO.setStatus(course.getStatus());
                    detailedCourseDTO.setInstructorId(course.getInstructorId());
                    detailedCourseDTO.setCoverImgUrl(course.getCoverImgUrl());

                    List<Module> moduleList = moduleRepository.findAllByCourseId(courseId);

                    if(!moduleList.isEmpty()){

                        AtomicReference<List<ModuleResponseDTO>> moduleResponseDTOList1 = new AtomicReference<>(new ArrayList<>());

                        moduleList.forEach(module -> {

                            ModuleResponseDTO moduleResponseDTO = new ModuleResponseDTO();
                            moduleResponseDTO.setId(module.getId());
                            moduleResponseDTO.setModuleCode(module.getModuleCode());
                            moduleResponseDTO.setModuleName(module.getModuleName());
                            moduleResponseDTO.setCourseId(module.getCourseId());

                            //fetches contents from content-service
                            List<VideoDTO> videoDTOList = iContent.getAllVideos(module.getId()).getBody();
                            if(videoDTOList != null)
                                moduleResponseDTO.setVideoDTOList(videoDTOList);

                            List<ReadingDTO> readingDTOList = iContent.getAllReadingsByModule(module.getId()).getBody();
                            if(readingDTOList != null)
                                moduleResponseDTO.setReadingDTOList(readingDTOList);

                            QuizDTO quizDTO = iContent.getQuizByModuleId(module.getId()).getBody();
                            if(quizDTO != null) {

                                moduleResponseDTO.setQuizDTO(quizDTO);
                            }
                            if(detailedCourseDTO.getModuleResponseDTOList() == null)
                                detailedCourseDTO.setModuleResponseDTOList(new ArrayList<>());

                            moduleResponseDTOList1.set(detailedCourseDTO.getModuleResponseDTOList());
                            moduleResponseDTOList1.get().add(moduleResponseDTO);

                        });
                        List<ModuleResponseDTO> moduleResponseDTOList = moduleResponseDTOList1.get();
                        //Modules and it's contents are assign to the relevant course.
                        detailedCourseDTO.setModuleResponseDTOList(moduleResponseDTOList);

                    }
                    return detailedCourseDTO;
                }else
                    throw new NullPointerException("Course not found");
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }


    public String cloudinaryUrl(CourseWrapper courseWrapper) throws IOException {
        Map<String, String> uploadOptions = new HashMap<>();
        uploadOptions.put("resource_type","image");

        Map uploadResult = cloudinary.uploader().upload(courseWrapper.getFile().getBytes(), uploadOptions);
        return uploadResult.get("url").toString();
    }
}
