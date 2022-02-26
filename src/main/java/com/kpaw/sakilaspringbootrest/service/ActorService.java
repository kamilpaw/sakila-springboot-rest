package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import com.kpaw.sakilaspringbootrest.web.model.pages.ActorPagedList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ActorService {

    Page<Actor> findAll(PageRequest pageRequest);

    Actor findById(short id);

    void save(Actor actor);

    void deleteById(short id);

    ActorPagedList findActorsByFilmId(int id, PageRequest pageRequest);

    ActorPagedList findActorsByFirstNameOrLastName(String name, PageRequest pageRequest);
}
