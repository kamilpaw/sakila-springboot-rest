package com.kpaw.sakilaspringbootrest.repository;

import com.kpaw.sakilaspringbootrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
}
