package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.movie.ActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ActorServiceImplTest {

    @Mock
    ActorRepository actorRepository;

    @Captor
    ArgumentCaptor<PageRequest> captor;

    @InjectMocks
    ActorServiceImpl service;

    Actor actor, actor1;
    List<Actor> actorList;
    Page<Actor> actorPage;

    @BeforeEach
    void setUp() {
        actor = new Actor((short) 1, "firstName", "lastName");
        actor1 = new Actor((short) 2, "firstName", "lastName");
        actorList = new ArrayList<>();
        actorList.add(actor);
        actorList.add(actor1);
       actorPage = new SetUpPage<>(actorList);
    }


    @Test
    void findAll() {
        given(actorRepository.findAll(captor.capture())).willReturn(actorPage);
        service.findAll(captor.capture());
        then(actorRepository).should().findAll(captor.capture());
        assertEquals(2, actorRepository.findAll(captor.capture()).getContent().size());
    }

    @Test
    void findById() {
        given(actorRepository.findById(actor.getActorId())).willReturn(Optional.of(actor));
        service.findById(actor.getActorId());
        then(actorRepository).should().findById(actor.getActorId());
        assertThat(actorRepository.findById(actor.getActorId())).isNotEmpty();
    }

    @Test
    void findByIdNotFound(){
        Optional<Actor> result = actorRepository.findById(anyShort());
        if(result.isEmpty()) {
            assertThrows(EntityNotFoundExc.class, () -> service.findById((short) 1));
        }
    }

    @Test
    void save() {
        service.save(actor);
        then(actorRepository).should().save(actor);
    }

    @Test
    void deleteById() {
        service.deleteById(actor.getActorId());
        then(actorRepository).should().deleteById(actor.getActorId());
    }

    @Test
    void findActorsByFilmId() {
        given(actorRepository.findActorsByFilmId(anyInt(),captor.capture())).willReturn(actorPage);
        service.findActorsByFilmId(anyInt(), captor.capture());
        then(actorRepository).should().findActorsByFilmId(anyInt(),captor.capture());
        assertEquals(2, actorRepository.findActorsByFilmId(anyInt(),captor.capture()).getContent().size());
    }

}