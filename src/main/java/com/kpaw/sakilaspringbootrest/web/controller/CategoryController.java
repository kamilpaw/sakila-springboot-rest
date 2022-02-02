package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.domain.Category;
import com.kpaw.sakilaspringbootrest.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/categories/{categoryId}")
    public Category findById(@PathVariable byte categoryId){
        return categoryService.findById(categoryId);
    }

    @PostMapping("/categories")
    public String saveNewCategory(@RequestBody Category category){
        category.setCategoryId((byte) 0);
        categoryService.save(category);
        return "category saved: " + category;
    }

    @PutMapping("/categories")
    public String updateCategory(@RequestBody Category category){
        categoryService.save(category);
        return "category updated: " + category;
    }

    @DeleteMapping("/categories/{categoryId}")
    public String deleteCategory(@PathVariable byte categoryId){
        categoryService.deleteById(categoryId);
        return "category with id " + categoryId + " deleted";
    }

}
