package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.Actor;

import java.util.List;

public interface ActorService {

    List<Actor> findAll();
    Actor findById(short id);
    void save(Actor actor);
    void deleteById(short id);
    List<Actor> findActorsByFilmId(int id);
}
