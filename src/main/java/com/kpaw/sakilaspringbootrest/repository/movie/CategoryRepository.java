package com.kpaw.sakilaspringbootrest.repository.movie;

import com.kpaw.sakilaspringbootrest.domain.movie.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="categories")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
