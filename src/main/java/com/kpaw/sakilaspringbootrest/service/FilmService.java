package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.web.model.FilmPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface FilmService {


    FilmPagedList findAll(PageRequest pageRequest);

    Film findByID(int id);

    void save(Film film);

    void deleteById(int id);

    FilmPagedList findFilmsByActorId(int id, PageRequest pageRequest);

    List<Film> findFilmsByCategoryId(int id);

    FilmPagedList listFilmsByTitle(String title, PageRequest pageRequest);


}
