package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.web.model.pages.FilmPagedList;
import org.springframework.data.domain.PageRequest;

public interface FilmService {


    FilmPagedList findAll(PageRequest pageRequest);

    Film findByID(int id);

    void save(Film film);

    void deleteById(int id);

    FilmPagedList findFilmsByActorId(int id, PageRequest pageRequest);

    FilmPagedList findFilmsByCategoryId(int id, PageRequest pageRequest);

    FilmPagedList findFilmsByTitle(String title, PageRequest pageRequest);


}
