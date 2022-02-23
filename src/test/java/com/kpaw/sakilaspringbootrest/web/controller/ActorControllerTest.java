package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import com.kpaw.sakilaspringbootrest.service.ActorService;
import com.kpaw.sakilaspringbootrest.web.model.pages.ActorPagedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ActorController.class)
class ActorControllerTest extends ControllerTest {

    @MockBean
    ActorService actorService;

    Actor actor1;
    Actor actor2;
    List<Actor> actors;
    ActorPagedList actorPagedList;

    @Captor
    ArgumentCaptor<PageRequest> pageRequestArgumentCaptor;

    @Test
    @BeforeEach
    void setUp() {
        actor1 = new Actor((short) 1, "firstName1", "lastName1");
        actor2 = new Actor((short) 2, "firstName2", "lastName2");
        actors = new ArrayList<>();
        actors.add(actor1);
        actors.add(actor2);
        actorPagedList = new ActorPagedList(actors, PageRequest.of(1, 1), 2);

    }

    @AfterEach
    void tearDown() {
        reset(actorService);
    }

    @Test
    void findAll() throws Exception {
        given(actorService.findAll(pageRequestArgumentCaptor.capture())).willReturn(actorPagedList);
        MvcResult mvcResult = mockMvc.perform(get("/actors")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].actorId", is(Integer.valueOf(actor1.getActorId()))))
                .andReturn();
    }

    @Test
    void findActor() throws Exception {
        given(actorService.findById(actor1.getActorId())).willReturn(actor1);
        mockMvc.perform(get("/actors/" + actor1.getActorId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.actorId", is(Integer.valueOf(actor1.getActorId()))))
                .andReturn();
    }

    @Test
    void saveNewActor() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/actors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.mapToJson(actor1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        actor1.setActorId(null);
        String content = mvcResult.getResponse().getContentAsString();
        Actor actor = this.mapFromJson(content, Actor.class);
        assertEquals(actor1.toString(), actor.toString());
    }

    @Test
    void updateActor() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/actors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.mapToJson(actor1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Actor actor = this.mapFromJson(content, Actor.class);
        assertEquals(actor1.toString(), actor.toString());
    }

    @Test
    void deleteActor() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/actors/" + actor1.getActorId()))
                .andExpect(status().isOk())
                .andReturn();
        then(actorService).should().deleteById(actor1.getActorId());
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Actor with Id " + actor1.getActorId() + " deleted", content);
    }

    @Test
    void findActorsByFilmId() throws Exception {
       given(actorService.findActorsByFilmId(anyInt(), pageRequestArgumentCaptor.capture())).willReturn(actorPagedList);
       mockMvc.perform(get("/actors/films/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].actorId", is(Integer.valueOf(actor1.getActorId()))))
                .andReturn();

    }

    @Test
    void searchActorsByFirstNameAndLastName() throws Exception {
        given(actorService.findActorsByFirstNameOrLastName(anyString(),pageRequestArgumentCaptor.capture())).willReturn(actorPagedList);
        mockMvc.perform(get("/actors/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].actorId", is(Integer.valueOf(actor1.getActorId()))))
                .andReturn();
    }
}