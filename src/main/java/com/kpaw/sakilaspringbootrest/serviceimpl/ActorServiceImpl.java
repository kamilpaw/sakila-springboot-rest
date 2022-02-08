package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import com.kpaw.sakilaspringbootrest.repository.movie.ActorRepository;
import com.kpaw.sakilaspringbootrest.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findById(short theId) {
        Optional<Actor> result = actorRepository.findById(theId);
      /*  if(!result.isPresent()){
            throw new ActorNotFoundException();
        }
       */
        Actor actor = result.get();
        return actor;
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
    public List<Actor> findActorsByFilmId(int id) {
        return actorRepository.findActorsByFilmId(id);
    }
}
