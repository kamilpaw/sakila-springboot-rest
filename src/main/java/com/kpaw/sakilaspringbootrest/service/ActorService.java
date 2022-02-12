package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import com.kpaw.sakilaspringbootrest.web.model.ActorPagedList;
import org.springframework.data.domain.PageRequest;

public interface ActorService {

    ActorPagedList findAll(PageRequest pageRequest);

    Actor findById(short id);

    void save(Actor actor);

    void deleteById(short id);

    ActorPagedList findActorsByFilmId(int id, PageRequest pageRequest);

    ActorPagedList findActorsByFirstNameAndLastName(String name, PageRequest pageRequest);
}
