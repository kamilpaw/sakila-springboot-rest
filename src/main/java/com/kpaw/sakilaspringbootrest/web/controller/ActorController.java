package com.kpaw.sakilaspringbootrest.web.controller;


import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import com.kpaw.sakilaspringbootrest.service.ActorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.kpaw.sakilaspringbootrest.web.model.pages.PageSizeAndNumber.pageNumber;
import static com.kpaw.sakilaspringbootrest.web.model.pages.PageSizeAndNumber.pageSize;

@RestController
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors")
    public Page<Actor> findAll(@RequestParam(required = false) Integer pageNumber,
                               @RequestParam(required = false) Integer pageSize) {

        return actorService.findAll(PageRequest.of(pageNumber(pageNumber), pageSize(pageSize)));
    }

    @GetMapping("/actors/{actorId}")
    public Actor findActor(@PathVariable short actorId) {
        return actorService.findById(actorId);
    }


    @PostMapping("/actors")
    public Actor saveNewActor(@RequestBody Actor actor) {
        actor.setActorId((null));
        actorService.save(actor);
        return actor;
    }

    @PutMapping("/actors")
    public Actor updateActor(@RequestBody Actor actor) {
        actorService.save(actor);
        return actor;
    }

    @DeleteMapping("/actors/{actorId}")
    public String deleteActor(@PathVariable short actorId) {
        actorService.deleteById(actorId);
        return "Actor with Id " + actorId + " deleted";
    }

    @GetMapping("/actors/films/{filmId}")
    public Page<Actor> findActorsByFilmId(@PathVariable int filmId,
                                          @RequestParam(required = false) Integer pageNumber,
                                          @RequestParam(required = false) Integer pageSize) {
        return actorService.findActorsByFilmId(filmId, PageRequest.of(pageNumber(pageNumber), pageSize(pageSize)));
    }

    @GetMapping("/actors/search")
    public Page<Actor> searchActorsByFirstNameAndLastName(@RequestParam(required = false) Integer pageNumber,
                                                          @RequestParam(required = false) Integer pageSize,
                                                          @RequestParam(defaultValue = "") String name) {
        return actorService.findActorsByFirstNameOrLastName(name, PageRequest.of(pageNumber(pageNumber), pageSize(pageSize)));
    }
}
