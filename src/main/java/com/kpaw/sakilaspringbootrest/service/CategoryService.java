package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.movie.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    Category findById(byte id);
    void save(Category category);
    void deleteById(byte id);
}
