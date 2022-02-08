package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.service.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public List<Film> findAllFilmDTOs() {
        return filmService.findAll();
    }

    @GetMapping("/films/{filmId}")
    public Film findFilmDTOById(@PathVariable int filmId) {
        return filmService.findByID(filmId);
    }

    @PostMapping("/films")
    public String saveNewFilm(@RequestBody Film film) {
        film.setFilmId(0);
        filmService.save(film);
        return "film saved: " + film;
    }

    @PutMapping("/films")
    public String updateFilm(@RequestBody Film film) {
        filmService.save(film);
        return "film updated: " + film;
    }

    @DeleteMapping("/films/{filmId}")
    public String deleteFilm(@PathVariable int filmId) {
        filmService.deleteById(filmId);
        return "film with id " + filmId + " deleted";
    }

    @GetMapping("films/actors/{actorId}")
    public List<Film> findFilmsByActorId(@PathVariable int actorId) {
        return filmService.findFilmsByActorId(actorId);
    }

    @GetMapping("films/categories/{categoryId}")
    public List<Film> findFilmsByCategoryId(@PathVariable int categoryId) {
        return filmService.findFilmsByCategoryId(categoryId);
    }
}
