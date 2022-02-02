package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.movie.Language;

import java.util.List;

public interface LanguageService {

    List<Language> findAll();
    Language findById(byte id);
    void save(Language language);
    void deleteById(byte id);
}
