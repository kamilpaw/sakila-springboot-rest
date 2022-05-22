package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ActorService {

    Page<Actor> findAll(PageRequest pageRequest);

    Actor findById(short id);

    void save(Actor actor);

    void deleteById(short id);

    Page<Actor> findActorsByFilmId(int id, PageRequest pageRequest);

    Page<Actor> findActorsByFirstNameOrLastName(String name, PageRequest pageRequest);
}
