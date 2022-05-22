package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface FilmService {


    Page<Film> findAll(PageRequest pageRequest);

    Film findByID(int id);

    void save(Film film);

    void deleteById(int id);

    Page<Film> findFilmsByActorId(int id, PageRequest pageRequest);

    Page<Film> findFilmsByCategoryId(int id, PageRequest pageRequest);

    Page<Film> findFilmsByTitle(String title, PageRequest pageRequest);

}
