package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.movie.ActorRepository;
import com.kpaw.sakilaspringbootrest.service.ActorService;
import com.kpaw.sakilaspringbootrest.web.model.pages.ActorPagedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public ActorPagedList findAll(PageRequest pageRequest) {
        Page<Actor> actorPage = actorRepository.findAll(pageRequest);
        return new ActorPagedList(actorPage.getContent(),
                PageRequest.of(actorPage.getPageable().getPageNumber(), actorPage.getPageable().getPageSize()),
                actorPage.getTotalElements());
    }

    @Override
    public Actor findById(short theId) {
        Optional<Actor> result = actorRepository.findById(theId);
        if (result.isEmpty()) {
            throw new EntityNotFoundExc("Actor", theId);
        }
        return result.get();
    }

    @Override
    public void save(Actor actor) {
        actorRepository.save(actor);
    }

    @Override
    public void deleteById(short theId) {
        actorRepository.deleteById(theId);
    }

    @Override
    public ActorPagedList findActorsByFilmId(int id, PageRequest pageRequest) {
        Page<Actor> actorPage = actorRepository.findActorsByFilmId(id, pageRequest);
        return new ActorPagedList(actorPage.getContent(),
                PageRequest.of(actorPage.getPageable().getPageNumber(), actorPage.getPageable().getPageSize()),
                actorPage.getTotalElements());
    }

    @Override
    public ActorPagedList findActorsByFirstNameOrLastName(String name, PageRequest pageRequest) {
        Page<Actor> actorPage = actorRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(name, name, pageRequest);
        return new ActorPagedList(actorPage.getContent(),
                PageRequest.of(actorPage.getPageable().getPageNumber(), actorPage.getPageable().getPageSize()),
                actorPage.getTotalElements());
    }
}
