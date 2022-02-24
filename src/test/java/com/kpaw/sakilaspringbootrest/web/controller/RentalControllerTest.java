package com.kpaw.sakilaspringbootrest.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpaw.sakilaspringbootrest.service.RentalService;
import com.kpaw.sakilaspringbootrest.web.model.dtos.CustomerDTO;
import com.kpaw.sakilaspringbootrest.web.model.dtos.RentalDTO;
import com.kpaw.sakilaspringbootrest.web.model.pages.RentalPagedList;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RentalController.class)
class RentalControllerTest extends ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RentalService rentalService;

    @Captor
    ArgumentCaptor<PageRequest> pageRequestArgumentCaptor;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    RentalDTO rental, rental2;
    List<RentalDTO> rentalList;
    RentalPagedList rentalPagedList;
    CustomerDTO customer;

    @BeforeEach
    void setUp() {
        customer = new CustomerDTO((short)1);
        rental = new RentalDTO(1, new Timestamp(1), 12, customer, new Timestamp(1), (byte) 1, new Date(1));
        rental2 = new RentalDTO(2, new Timestamp(1), 1, customer, new Timestamp(1), (byte) 1, new Date(1));
        rentalList = new ArrayList<>();
        rentalList.add(rental);
        rentalList.add(rental2);
        rentalPagedList = new RentalPagedList(rentalList, PageRequest.of(1, 1), 2);
    }

    @AfterEach
    void tearDown() {
        reset(rentalService);
    }

    @Test
    void findAll() throws Exception {
        given(rentalService.findAll(pageRequestArgumentCaptor.capture())).willReturn(rentalPagedList);
        mockMvc.perform(get("/rentals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].rentalId", is(rental.getRentalId())));
    }

    @Test
    void findById() throws Exception {
        given(rentalService.findById(anyInt())).willReturn(rental);
        mockMvc.perform(get("/rentals/" + rental.getRentalId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.rentalId", is(rental.getRentalId())))
                .andExpect(jsonPath("$.inventoryId", is(rental.getInventoryId())));
        then(rentalService).should().findById(rental.getRentalId());
    }

    @Test
    void saveNewRental() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/rentals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(rental)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        rental.setRentalId(null);
        String content = mvcResult.getResponse().getContentAsString();
        RentalDTO rentalDTO = mapFromJson(content, RentalDTO.class);
        assertEquals(rental.getRentalId(), rentalDTO.getRentalId());
    }

    @Test
    void updateRental() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/rentals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(rental)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        RentalDTO rentalDTO = mapFromJson(content, RentalDTO.class);
        assertEquals(rental.getRentalId(), rentalDTO.getRentalId());
}

    @Test
    void deleteRental() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/rentals/" + rental.getRentalId()))
                .andExpect(status().isOk())
                .andReturn();
        then(rentalService).should().deleteById(rental.getRentalId());
        assertEquals("rental with id " + rental.getRentalId() + " deleted", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void findRentalsByCustomerID() throws Exception {
        given(rentalService.findRentalsByCustomerId(anyInt(), pageRequestArgumentCaptor.capture())).willReturn(rentalPagedList);
        mockMvc.perform(get("/rentals/customers/" + customer.getCustomerId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].rentalId", is(rental.getRentalId())));
        then(rentalService).should().findRentalsByCustomerId(anyInt(), pageRequestArgumentCaptor.capture());
    }
}