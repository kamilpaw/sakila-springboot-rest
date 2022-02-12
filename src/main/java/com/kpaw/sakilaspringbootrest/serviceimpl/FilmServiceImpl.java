package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.movie.FilmRepository;
import com.kpaw.sakilaspringbootrest.service.FilmService;
import com.kpaw.sakilaspringbootrest.web.model.FilmPagedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;


    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public FilmPagedList findAll(PageRequest pageRequest) {
        Page<Film> filmPage = filmRepository.findAll(pageRequest);
        return new FilmPagedList(filmPage.getContent(),
                PageRequest.of(filmPage.getPageable().getPageNumber(), filmPage.getPageable().getPageSize()),
                filmPage.getTotalElements());
    }

    @Override
    public FilmPagedList findFilmsByTitle(String title, PageRequest pageRequest) {
        Page<Film> filmPage = filmRepository.findByTitleContainsAllIgnoreCase(title, pageRequest);
        return new FilmPagedList(filmPage.getContent(),
                PageRequest.of(filmPage.getPageable().getPageNumber(), filmPage.getPageable().getPageSize()),
                filmPage.getTotalElements());
    }

    @Override
    public FilmPagedList findFilmsByActorId(int id, PageRequest pageRequest) {
        Page<Film> filmPage = filmRepository.findFilmsByActorId(id, pageRequest);
        return new FilmPagedList(filmPage.getContent(),
                PageRequest.of(filmPage.getPageable().getPageNumber(), filmPage.getPageable().getPageSize()),
                filmPage.getTotalElements());
    }

    @Override
    public FilmPagedList findFilmsByCategoryId(int id, PageRequest pageRequest) {
        Page<Film> filmPage = filmRepository.findFilmsByCategoryId(id, pageRequest);
        return new FilmPagedList(filmPage.getContent(),
                PageRequest.of(filmPage.getPageable().getPageNumber(), filmPage.getPageable().getPageSize()),
                filmPage.getTotalElements());
    }


    @Override
    public Film findByID(int id) {
        Optional<Film> result = filmRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundExc("Film", id);
        }
        return result.get();
    }

    @Override
    public void save(Film film) {
        filmRepository.save(film);
    }


    @Override
    public void deleteById(int id) {
        filmRepository.deleteById(id);
    }


}






















