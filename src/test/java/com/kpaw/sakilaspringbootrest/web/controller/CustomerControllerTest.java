package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.domain.location.Address;
import com.kpaw.sakilaspringbootrest.service.AddressService;
import com.kpaw.sakilaspringbootrest.service.CustomerService;
import com.kpaw.sakilaspringbootrest.web.model.dtos.CustomerDTO;
import com.kpaw.sakilaspringbootrest.web.model.pages.CustomerPagedList;
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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest extends ControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @MockBean
    AddressService addressService;

    @Captor
    ArgumentCaptor<PageRequest> pageRequestArgumentCaptor;

    CustomerDTO customer1;
    CustomerDTO customer2;
    List<CustomerDTO> customerList;
    CustomerPagedList customerPagedList;

    @BeforeEach
    void setUp() {
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        Address address = new Address();

        customer1 = new CustomerDTO((short) 1, (byte) 1, "firstName", "lastName", "email", address, true, timestamp, date);
        customer2 = new CustomerDTO((short) 2, (byte) 2, "firstName2", "lastName2", "email2", address, true, timestamp, date);
        customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerPagedList = new CustomerPagedList(customerList, PageRequest.of(1, 1), 2);

    }

    @AfterEach
    void tearDown() {
        reset(customerService);
        reset(addressService);
    }

    @Test
    void findAll() throws Exception {
        given(customerService.findAll(pageRequestArgumentCaptor.capture())).willReturn(customerPagedList);
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    void findById() throws Exception {
        given(customerService.findById(customer1.getCustomerId())).willReturn(customer1);
        MvcResult mvcResult = mockMvc.perform(get("/customers/" + customer1.getCustomerId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        CustomerDTO customerDTO = this.mapFromJson(content, CustomerDTO.class);
        assertEquals(customer1.toString(), customerDTO.toString());
    }

    @Test
    void saveNewCustomer() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.mapToJson(customer1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        customer1.setCustomerId(null);
        String content = mvcResult.getResponse().getContentAsString();
        CustomerDTO customerDTO = mapFromJson(content, CustomerDTO.class);
        assertEquals(customer1.toString(), customerDTO.toString());
    }

    @Test
    void updateCustomer() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(customer1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        CustomerDTO customerDTO = mapFromJson(content, CustomerDTO.class);
        assertEquals(customer1.toString(), customerDTO.toString());
    }

    @Test
    void deleteCustomer() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/customers/" + customer1.getCustomerId()))
                .andExpect(status().isOk())
                .andReturn();
        then(customerService).should().deleteById(customer1.getCustomerId());
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Customer with id " + customer1.getCustomerId() + " deleted", content);
    }

    @Test
    void findByFirstNameOrLastName() throws Exception {
        given(customerService.findCustomerByFirstNameOrLastName(anyString(), pageRequestArgumentCaptor.capture())).willReturn(customerPagedList);
        mockMvc.perform(get("/customers/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andReturn();
    }
}