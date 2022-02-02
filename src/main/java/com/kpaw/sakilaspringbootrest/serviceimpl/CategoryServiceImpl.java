package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.Category;
import com.kpaw.sakilaspringbootrest.repository.CategoryRepository;
import com.kpaw.sakilaspringbootrest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(byte id) {
        Optional<Category> result = categoryRepository.findById(id);
     /*   if(!result.isPresent()){
            throw new CategoryNotFoundException("Categroy with id " + id + " not found");
        }
      */
        return result.get();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(byte id) {
        categoryRepository.deleteById(id);
    }
}
