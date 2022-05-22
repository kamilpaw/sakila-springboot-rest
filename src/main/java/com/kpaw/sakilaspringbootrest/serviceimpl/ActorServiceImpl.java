package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.movie.ActorRepository;
import com.kpaw.sakilaspringbootrest.service.ActorService;
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
    public Page<Actor> findAll(PageRequest pageRequest) {
        return actorRepository.findAll(pageRequest);
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
    public Page<Actor> findActorsByFilmId(int id, PageRequest pageRequest) {
        return actorRepository.findActorsByFilmId(id, pageRequest);
    }

    @Override
    public Page<Actor> findActorsByFirstNameOrLastName(String name, PageRequest pageRequest) {
        return actorRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(name, name, pageRequest);
    }
}
