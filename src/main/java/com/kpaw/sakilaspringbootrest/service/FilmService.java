package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.Film;

import java.util.List;

public interface FilmService {


    List<Film> findall();
    Film findByID(int id);
    void save(Film film);
    void deleteById(int id);
    List<Film> findFilmsByActorId(int id);
    List<Film> findFilmsByCategoryId(int id);
}
