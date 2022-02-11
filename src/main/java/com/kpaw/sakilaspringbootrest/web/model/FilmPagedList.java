package com.kpaw.sakilaspringbootrest.web.model;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class FilmPagedList extends PageImpl<Film> {


    public FilmPagedList(List<Film> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public FilmPagedList(List<Film> content) {
        super(content);
    }
}
