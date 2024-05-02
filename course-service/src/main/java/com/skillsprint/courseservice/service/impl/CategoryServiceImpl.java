package com.skillsprint.courseservice.service.impl;

import com.skillsprint.courseservice.dto.CategoryDTO;
import com.skillsprint.courseservice.model.Category;
import com.skillsprint.courseservice.repository.CategoryRepository;
import com.skillsprint.courseservice.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public Object addCategory(CategoryDTO categoryDTO) {

        try{
            Category category = categoryRepository.findByCategoryCode(categoryDTO.getCategoryCode());

            if(category != null)
                return ResponseEntity.status(HttpStatus.FOUND).body("Category"+ categoryDTO.getCategoryCode()
                        + "Already Available");
            else{
                Category category1 = mapper.map(categoryDTO, Category.class);
                categoryRepository.save(category1);
                return ResponseEntity.status(HttpStatus.OK).body("Category"+ categoryDTO.getCategoryCode()
                        + "Added successfully");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Object getCategoryByCategoryCode(String categoryCode) {
        try{
            Category category = categoryRepository.findByCategoryCode(categoryCode);
            CategoryDTO categoryDTO = mapper.map(category, CategoryDTO.class);

            if(category != null)
                return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found for the code"
                        + categoryCode);
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Object editCategory(CategoryDTO categoryDTO) {
        try{
            Category category = categoryRepository.findByCategoryCode(categoryDTO.getCategoryCode());

            if(category != null){
                category.setCategoryCode(categoryDTO.getCategoryCode());
                category.setCategoryName(categoryDTO.getCategoryName());

                categoryRepository.save(category);
                return ResponseEntity.status(HttpStatus.OK).body("Category Updated Successfully.");
            }else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");

        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Object deleteCategoryByCategoryCode(String categoryCode) {
        try{
            Category category = categoryRepository.findByCategoryCode(categoryCode);

            if(category != null ) {
                categoryRepository.delete(category);
                return ResponseEntity.status(HttpStatus.OK).body("Category " + categoryCode
                        + " deleted Successfully.");
            }
            else
                return ResponseEntity.status(HttpStatus.OK).body("Category " + categoryCode + " not found");
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

}
