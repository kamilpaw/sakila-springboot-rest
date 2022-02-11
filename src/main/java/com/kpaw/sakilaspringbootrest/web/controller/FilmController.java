package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.service.FilmService;
import com.kpaw.sakilaspringbootrest.web.model.FilmPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/films")
@RestController
public class FilmController {


    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 5;
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("")
    public FilmPagedList findAllFilms(@RequestParam(required = false) Integer pageNumber,
                                      @RequestParam(required = false) Integer pageSize) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return filmService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/{filmId}")
    public Film findFilmById(@PathVariable int filmId) {
        return filmService.findByID(filmId);
    }

    @PostMapping("")
    public ResponseEntity<Film> saveNewFilm(@RequestBody Film film) {
        film.setFilmId(null);
        filmService.save(film);
        return new ResponseEntity<>(film, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Film> updateFilm(@RequestBody Film film) {
        filmService.save(film);
        return new ResponseEntity<>(film, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{filmId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteFilm(@PathVariable int filmId) {
        filmService.deleteById(filmId);
        return "film with id " + filmId + " deleted";
    }

    @GetMapping("/actors/{actorId}")
    public FilmPagedList findFilmsByActorId(@PathVariable int actorId,
                                            @RequestParam(required = false) Integer pageNumber,
                                            @RequestParam(required = false) Integer pageSize) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return filmService.findFilmsByActorId(actorId, PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/categories/{categoryId}")
    public List<Film> findFilmsByCategoryId(@PathVariable int categoryId) {
        return filmService.findFilmsByCategoryId(categoryId);
    }

    @GetMapping("/search")
    public FilmPagedList listFilms(@RequestParam(required = false) Integer pageNumber,
                                   @RequestParam(required = false) Integer pageSize,
                                   @RequestParam(required = false) String title) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return filmService.listFilmsByTitle(title, PageRequest.of(pageNumber, pageSize));

    }
}
