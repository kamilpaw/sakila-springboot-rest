package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.repository.movie.FilmRepository;
import com.kpaw.sakilaspringbootrest.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;


    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film findByID(int id) {
        Optional<Film> result = filmRepository.findById(id);
    /*    if (result.isPresent()){
            throw new FilmNotFoundException ("Film with id " + id + " not found");
        }
     */
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

    @Override
    public List<Film> findFilmsByActorId(int id) {
        return filmRepository.findFilmsByActorId(id);
    }

    @Override
    public List<Film> findFilmsByCategoryId(int id) {
        return filmRepository.findFilmsByCategoryId(id);
    }

}






















