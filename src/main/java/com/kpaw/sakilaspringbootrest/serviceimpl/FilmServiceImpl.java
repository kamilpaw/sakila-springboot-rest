package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.movie.FilmRepository;
import com.kpaw.sakilaspringbootrest.service.FilmService;
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
    public Page<Film> findAll(PageRequest pageRequest) {
        return filmRepository.findAll(pageRequest);
    }

    @Override
    public Page<Film> findFilmsByTitle(String title, PageRequest pageRequest) {
        return filmRepository.findByTitleContainsAllIgnoreCase(title, pageRequest);
    }

    @Override
    public Page<Film> findFilmsByActorId(int id, PageRequest pageRequest) {
        return filmRepository.findFilmsByActorId(id, pageRequest);
    }

    @Override
    public Page<Film> findFilmsByCategoryId(int id, PageRequest pageRequest) {
        return filmRepository.findFilmsByCategoryId(id, pageRequest);
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






















