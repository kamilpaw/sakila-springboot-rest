package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.movie.FilmRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class FilmServiceImplTest {

    @Mock
    FilmRepository filmRepository;

    @InjectMocks
    FilmServiceImpl service;

    @Captor
    ArgumentCaptor<PageRequest> captor;

    Film film, film1;
    List<Film> filmList;
    Page<Film> page;


    @BeforeEach
    void setUp() {
        film = new Film();
        film1 = new Film();
        filmList = new ArrayList<>();
        filmList.add(film);
        filmList.add(film1);
        page = new SetUpPage<>(filmList);
    }

    @Test
    void findAll() {
        given(filmRepository.findAll(captor.capture())).willReturn(page);
        service.findAll(captor.capture());
        then(filmRepository).should().findAll(captor.capture());
        assertEquals(2,filmRepository.findAll(captor.capture()).getContent().size());
    }

    @Test
    void findFilmsByTitle() {
        given(filmRepository.findByTitleContainsAllIgnoreCase(anyString(), captor.capture())).willReturn(page);
        service.findFilmsByTitle(anyString(), captor.capture());
        then(filmRepository).should().findByTitleContainsAllIgnoreCase(anyString(), captor.capture());
        assertEquals(2,filmRepository.findByTitleContainsAllIgnoreCase(anyString(), captor.capture()).getContent().size());
    }

    @Test
    void findFilmsByActorId() {
        given(filmRepository.findFilmsByActorId(anyInt(), captor.capture())).willReturn(page);
        service.findFilmsByActorId(anyInt(), captor.capture());
        then(filmRepository).should().findFilmsByActorId(anyInt(), captor.capture());
        assertEquals(2,filmRepository.findFilmsByActorId(anyInt(), captor.capture()).getContent().size());
    }

    @Test
    void findFilmsByIdNotFound(){
      Optional<Film> result = filmRepository.findById(1);
      if(result.isEmpty()){
          assertThrows(EntityNotFoundExc.class, ()-> service.findByID(1));
      }
    }


    @Test
    void findFilmsByCategoryId() {
        given(filmRepository.findFilmsByCategoryId(anyInt(), captor.capture())).willReturn(page);
        service.findFilmsByCategoryId(anyInt(), captor.capture());
        then(filmRepository).should().findFilmsByCategoryId(anyInt(), captor.capture());
        assertEquals(2,filmRepository.findFilmsByCategoryId(anyInt(), captor.capture()).getContent().size());
    }

    @Test
    void findByID() {
        given(filmRepository.findById(1)).willReturn(Optional.of(film));
        service.findByID(1);
        then(filmRepository).should().findById(1);
        assertThat(filmRepository).isNotNull();
    }

    @Test
    void save() {
        service.save(film);
        then(filmRepository).should().save(film);
    }

    @Test
    void deleteById() {
        service.deleteById(anyInt());
        then(filmRepository).should().deleteById(anyInt());
    }
}