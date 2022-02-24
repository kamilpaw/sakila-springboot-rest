package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.domain.location.Inventory;
import com.kpaw.sakilaspringbootrest.domain.location.Store;
import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.service.InventoryService;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.InventoryDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InventoryController.class)
class InventoryControllerTest extends ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    InventoryService inventoryService;

    @MockBean
    DTOMapper mapper;

    @Mock
    Film film;

    @Mock
    Store store;


    Date date;

    Inventory inventory1;
    Inventory inventory2;
    InventoryDTO inventoryDTO1;
    InventoryDTO inventoryDTO2;
    List<Inventory> inventoryList;
    List<InventoryDTO> inventoryDTOs;

    @BeforeEach
    void setUp() {
        date = Calendar.getInstance().getTime();
        inventory1 = new Inventory(1, film, store, date);
        inventory2 = new Inventory(2, film, store, date);
        inventoryDTO1 = new InventoryDTO(1, 1, (byte) 1, date);
        inventoryDTO2 = new InventoryDTO(2, 2, (byte) 2, date);
        inventoryList = new ArrayList<>();
        inventoryList.add(inventory1);
        inventoryList.add(inventory2);
        inventoryDTOs = new ArrayList<>();
        inventoryDTOs.add(inventoryDTO1);
        inventoryDTOs.add(inventoryDTO2);
    }

    @AfterEach
    void tearDown() {
        reset(inventoryService);
    }


    @Test
    void findAll() throws Exception {
        given(inventoryService.findAll()).willReturn(inventoryList);
        MvcResult mvcResult = mockMvc.perform(get("/inventory"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        InventoryDTO[] inventoryDTOS = this.mapFromJson(content, InventoryDTO[].class);
        assertEquals(inventoryDTOS.length, 2);
    }

    @Test
    void findById() throws Exception {

        given(mapper.toInventoryDto(inventoryService.findById(inventoryDTO1.getInventoryId()))).willReturn(inventoryDTO1);
        mockMvc.perform(get("/inventory/" + inventoryDTO1.getInventoryId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.inventoryId", is(inventoryDTO1.getInventoryId())))
                .andExpect(jsonPath("$.filmId", is(inventoryDTO1.getFilmId())))
                .andReturn();

    }

    @Test
    void saveNewInventory() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/inventory")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(inventoryDTO1)))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        inventoryDTO1.setInventoryId(0);
        String content = mvcResult.getResponse().getContentAsString();
        InventoryDTO inventoryDTO = this.mapFromJson(content, InventoryDTO.class);
        assertEquals(inventoryDTO.toString(), inventoryDTO1.toString());
    }

    @Test
    void deleteById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/inventory/" + inventory1.getInventoryId()))
                .andExpect(status().isOk())
                .andReturn();
        then(inventoryService).should().delete(inventory1.getInventoryId());
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "inventory with id " + inventory1.getInventoryId() + " deleted");


    }
}