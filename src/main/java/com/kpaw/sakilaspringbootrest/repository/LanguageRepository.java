package com.kpaw.sakilaspringbootrest.repository;

import com.kpaw.sakilaspringbootrest.domain.movie.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Byte> {
}
