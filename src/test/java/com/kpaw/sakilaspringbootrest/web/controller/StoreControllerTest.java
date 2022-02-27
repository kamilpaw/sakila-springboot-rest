package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.domain.location.Address;
import com.kpaw.sakilaspringbootrest.domain.location.Staff;
import com.kpaw.sakilaspringbootrest.domain.location.Store;
import com.kpaw.sakilaspringbootrest.service.StoreService;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.StoreDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StoreController.class)
class StoreControllerTest extends ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StoreService storeService;

    @MockBean
    DTOMapper mapper;

    Store store1, store2;
    List<Store> storeList;
    StoreDTO storeDTO1, storeDTO2;
    List<StoreDTO> storeDTOList;

    @BeforeEach
    void setUp() {
        store1 = new Store((byte) 1, new Staff(), new Address());
        store2 = new Store((byte) 2, new Staff(), new Address());
        storeList = new ArrayList<>();
        storeList.add(store1);
        storeList.add(store2);
        storeDTO1 = new StoreDTO((byte) 1, (byte) 1, new Address());
        storeDTO2 = new StoreDTO((byte) 2, (byte) 2, new Address());
        storeDTOList = new ArrayList<>();
        storeDTOList.add(storeDTO1);
        storeDTOList.add(storeDTO2);
    }

    @AfterEach
    void tearDown() {
        reset(storeService);
    }

    @Test
    void findAll() throws Exception {
        given(storeService.findAll()).willReturn(storeList);
        mockMvc.perform(get("/stores"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        then(storeService).should().findAll();
    }

    @Test
    void findById() throws Exception {
        given(mapper.toStoreDTO(storeService.finById(storeDTO1.getStoreId()))).willReturn(storeDTO1);
        mockMvc.perform(get("/stores/" + storeDTO1.getStoreId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.storeId", is(1)));
    }

    @Test
    void saveNewStore() throws Exception {
        mockMvc.perform(post("/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(storeDTO1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.storeId", is(0)))
                .andExpect(jsonPath("$.managerId", is(1)));
    }

    @Test
    void updateStore() throws Exception{
        mockMvc.perform(put("/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(storeDTO1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.storeId", is(1)))
                .andExpect(jsonPath("$.managerId", is(1)));
    }

    @Test
    void deleteById() throws Exception{
        MvcResult mvcResult = mockMvc.perform(delete("/stores/" + store1.getStoreId()))
                .andExpect(status().isOk())
                .andReturn();
        then(storeService).should().deleteById(store1.getStoreId());
        assertEquals("Store with id " + store1.getStoreId() + " deleted", mvcResult.getResponse().getContentAsString());
    }
}