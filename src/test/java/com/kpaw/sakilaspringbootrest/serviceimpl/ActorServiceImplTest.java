package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.movie.ActorRepository;
import com.kpaw.sakilaspringbootrest.web.model.pages.ActorPagedList;
import org.junit.jupiter.api.AfterEach;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ActorServiceImplTest {

    @Mock
    ActorRepository actorRepository;

    @Captor
    ArgumentCaptor<PageRequest> capture;

    @InjectMocks
    ActorServiceImpl service;

    Actor actor, actor1;
    List<Actor> actorList;
    ActorPagedList actorPagedList;
    Page<Actor> actorPage;

    @BeforeEach
    void setUp() {
        actor = new Actor((short) 1, "firstName", "lastName");
        actor1 = new Actor((short) 2, "firstName", "lastName");
        actorList = new ArrayList<>();
        actorList.add(actor);
        actorList.add(actor1);
        actorPagedList = new ActorPagedList(actorList, PageRequest.of(1,1),2);
        actorPage = new Page<Actor>() {
            @Override
            public int getTotalPages() {
                return 1;
            }

            @Override
            public long getTotalElements() {
                return 2;
            }

            @Override
            public <U> Page<U> map(Function<? super Actor, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 1;
            }

            @Override
            public int getSize() {
                return 2;
            }

            @Override
            public int getNumberOfElements() {
                return 2;
            }

            @Override
            public List<Actor> getContent() {
                return actorList;
            }

            @Override
            public boolean hasContent() {
                return true;
            }

            @Override
            public Sort getSort() {
                return Sort.unsorted();
            }

            @Override
            public boolean isFirst() {
                return true;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<Actor> iterator() {
                return null;
            }
        };
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        given(actorRepository.findAll(capture.capture())).willReturn(actorPage);
        service.findAll(capture.capture());
        then(actorRepository).should().findAll(capture.capture());
        assertEquals(2, actorRepository.findAll(capture.capture()).getContent().size());
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
        given(actorRepository.findActorsByFilmId(anyInt(),capture.capture())).willReturn(actorPage);
        service.findActorsByFilmId(anyInt(), capture.capture());
        then(actorRepository).should().findActorsByFilmId(anyInt(),capture.capture());
        assertEquals(2, actorRepository.findActorsByFilmId(anyInt(),capture.capture()).getContent().size());
    }

}