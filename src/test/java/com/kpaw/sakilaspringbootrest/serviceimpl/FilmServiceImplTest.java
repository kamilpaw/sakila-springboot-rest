package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.movie.FilmRepository;
import com.kpaw.sakilaspringbootrest.web.model.pages.FilmPagedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
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
import static org.junit.jupiter.api.Assertions.*;
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
    FilmPagedList filmPagedList;


    @BeforeEach
    void setUp() {
        film = new Film();
        film1 = new Film();
        filmList = new ArrayList<>();
        filmList.add(film);
        filmList.add(film1);
        filmPagedList = new FilmPagedList(filmList, PageRequest.of(1,1),2);
        page = new Page<Film>() {
            @Override
            public int getTotalPages() {
                return 1;
            }

            @Override
            public long getTotalElements() {
                return 2;
            }

            @Override
            public <U> Page<U> map(Function<? super Film, ? extends U> converter) {
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
            public List<Film> getContent() {
                return filmList;
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
                return true;
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
            public Iterator<Film> iterator() {
                return null;
            }
        };

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