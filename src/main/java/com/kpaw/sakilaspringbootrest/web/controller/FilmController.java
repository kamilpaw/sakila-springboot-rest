package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.domain.Film;
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
    public List<Film> findAll() {
        return filmService.findall();
    }

    @GetMapping("/films/{filmId}")
    public Film findById(@PathVariable int filmId) {
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

    @GetMapping("actors/{actorId}/films")
    public List<Film> findFilmByActorId(@PathVariable int actorId) {
        return filmService.findFilmsByActorId(actorId);
    }

    @GetMapping("categories/{categoryId}/films")
    public List<Film> findFilmsByCategoryId(@PathVariable int categoryId) {
        return filmService.findFilmsByCategoryId(categoryId);
    }
}
