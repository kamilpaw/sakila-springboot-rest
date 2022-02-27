package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import com.kpaw.sakilaspringbootrest.domain.movie.Category;
import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.domain.movie.Language;
import com.kpaw.sakilaspringbootrest.service.FilmService;
import com.kpaw.sakilaspringbootrest.web.model.pages.FilmPagedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilmController.class)
class FilmControllerTest extends ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FilmService filmService;

    @Captor
    ArgumentCaptor<PageRequest> pageRequestArgumentCaptor;

    Film film1, film2;

    List<Film> filmList;

    FilmPagedList filmPagedList;


    @BeforeEach
    void setUp() {
        Date date = Calendar.getInstance().getTime();
        Language language = new Language();
        List<Actor> actors = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        film1 = new Film(1, "title", "description", 1,
                language, language, (byte) 1, new BigDecimal("1"), (short) 1, new BigDecimal(1),
                "rating", "special futures", date, actors, categories);
        film2 = new Film();
        filmList = new ArrayList<>();
        filmList.add(film1);
        filmList.add(film2);
        filmPagedList = new FilmPagedList(filmList, PageRequest.of(1, 1), 2);
    }

    @AfterEach
    void tearDown() {
        reset(filmService);
    }


    @Test
    void findAllFilms() throws Exception {
        given(filmService.findAll(pageRequestArgumentCaptor.capture())).willReturn(filmPagedList);
        mockMvc.perform(get("/films"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    void findFilmById() throws Exception {
        mockMvc.perform(get("/films/" + film1.getFilmId()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void saveNewFilm() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/films/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(film1)))
                .andExpect(status().isOk())
                .andReturn();
        film1.setFilmId(null);
        String content = mvcResult.getResponse().getContentAsString();
        Film film = mapFromJson(content, Film.class);
        assertEquals(film.toString(), film1.toString());
    }

    @Test
    void updateFilm() throws Exception{
        MvcResult mvcResult = mockMvc.perform(put("/films/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(film1)))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Film film = mapFromJson(content, Film.class);
        assertEquals(film.toString(), film1.toString());
    }

    @Test
    void deleteFilm() throws Exception {
        mockMvc.perform(delete("/films/" + film1.getFilmId()))
                .andExpect(status().isOk())
                .andReturn();
        then(filmService).should().deleteById(film1.getFilmId());
    }


    @Test
    void findFilmsByActorId() throws Exception {
        given(filmService.findFilmsByActorId(anyInt(), pageRequestArgumentCaptor.capture())).willReturn(filmPagedList);
        mockMvc.perform(get("/films/actors/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].filmId", is(1)))
                .andReturn();

    }

    @Test
    void findFilmsByCategoryId() throws Exception {
        given(filmService.findFilmsByCategoryId(anyInt(), pageRequestArgumentCaptor.capture())).willReturn(filmPagedList);
        mockMvc.perform(get("/films/categories/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].filmId", is(1)))
                .andReturn();
    }

    @Test
    void searchFilmsByTitle() throws Exception {
        given(filmService.findFilmsByTitle(anyString(), pageRequestArgumentCaptor.capture())).willReturn(filmPagedList);
        mockMvc.perform(get("/films/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].filmId", is(1)))
                .andReturn();
    }
}