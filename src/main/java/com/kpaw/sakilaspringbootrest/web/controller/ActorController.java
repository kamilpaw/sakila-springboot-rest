package com.kpaw.sakilaspringbootrest.web.controller;


import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import com.kpaw.sakilaspringbootrest.service.ActorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors")
    public List<Actor> findAll() {
        return actorService.findAll();
    }

    @GetMapping("/actors/{actorId}")
    public Actor findActor(@PathVariable short actorId) {
        return actorService.findById(actorId);
    }


    @PostMapping("/actors")
    public String saveNewActor(@RequestBody Actor actor) {
        actor.setActorId((short) 0);
        actorService.save(actor);
        return "Saved new actor: " + actor;
    }

    @PutMapping("/actors")
    public String updateActor(@RequestBody Actor actor) {
        actorService.save(actor);
        return "Updated actor: " + actor;
    }

    @DeleteMapping("/actors/{actorId}")
    public String deleteActor(@PathVariable short actorId) {
        actorService.deleteById(actorId);
        return "Actor with Id " + actorId + " deleted";
    }

    @GetMapping("/actors/films/{filmId}")
    public List<Actor> findActorsByFilmId(@PathVariable int filmId) {
        return actorService.findActorsByFilmId(filmId);
    }
}
