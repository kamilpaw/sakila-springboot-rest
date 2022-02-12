package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.service.FilmService;
import com.kpaw.sakilaspringbootrest.web.model.FilmPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.kpaw.sakilaspringbootrest.web.model.PageSizeAndNumber.pageNumber;
import static com.kpaw.sakilaspringbootrest.web.model.PageSizeAndNumber.pageSize;


@RestController
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("films")
    public FilmPagedList findAllFilms(@RequestParam(required = false) Integer pageNumber,
                                      @RequestParam(required = false) Integer pageSize) {
        return filmService.findAll(PageRequest.of(pageNumber(pageNumber), pageSize(pageSize)));
    }


    @GetMapping("/films/{filmId}")
    public Film findFilmById(@PathVariable int filmId) {
        return filmService.findByID(filmId);
    }

    @PostMapping("/films")
    public Film saveNewFilm(@RequestBody Film film) {
        film.setFilmId(null);
        filmService.save(film);
        return film;
    }

    @PutMapping("/films")
    public Film updateFilm(@RequestBody Film film) {
        filmService.save(film);
        return film;
    }

    @DeleteMapping("/films/{filmId}")
    public String deleteFilm(@PathVariable int filmId) {
        filmService.deleteById(filmId);
        return "film with id " + filmId + " deleted";
    }

    @GetMapping("/films/actors/{actorId}")
    public FilmPagedList findFilmsByActorId(@PathVariable int actorId,
                                            @RequestParam(required = false) Integer pageNumber,
                                            @RequestParam(required = false) Integer pageSize) {
        return filmService.findFilmsByActorId(actorId, PageRequest.of(pageNumber(pageNumber), pageSize(pageSize)));
    }

    @GetMapping("/films/categories/{categoryId}")
    public FilmPagedList findFilmsByCategoryId(@PathVariable int categoryId,
                                               @RequestParam(required = false) Integer pageNumber,
                                               @RequestParam(required = false) Integer pageSize) {
        return filmService.findFilmsByCategoryId(categoryId, PageRequest.of(pageNumber(pageNumber), pageSize(pageSize)));
    }

    @GetMapping("/films/search")
    public FilmPagedList searchFilmsByTitle(@RequestParam(required = false) Integer pageNumber,
                                   @RequestParam(required = false) Integer pageSize,
                                   @RequestParam(defaultValue = "") String title) {
        return filmService.findFilmsByTitle(title, PageRequest.of(pageNumber(pageNumber), pageSize(pageSize)));

    }
}
