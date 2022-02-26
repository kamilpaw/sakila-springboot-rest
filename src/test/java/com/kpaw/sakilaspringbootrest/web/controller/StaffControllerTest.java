package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.domain.location.Address;
import com.kpaw.sakilaspringbootrest.domain.location.Staff;
import com.kpaw.sakilaspringbootrest.domain.location.Store;
import com.kpaw.sakilaspringbootrest.service.StaffService;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.StaffDTO;
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
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StaffController.class)
class StaffControllerTest extends ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StaffService staffService;

    @MockBean
    DTOMapper mapper;

    StaffDTO staffDTO, staffDTO2;
    List<StaffDTO> staffDTOList;
    Staff staff, staff2;
    List<Staff> staffList;


    @BeforeEach
    void setUp() {
        staff = new Staff((byte) 1, "firstName", "lastName", new Address(), new Byte[1], "email", new Store(), true, "username", "password", new Date());
        staff2 = new Staff((byte) 2, "firstName", "lastName", new Address(), new Byte[1], "email", new Store(), true, "username", "password", new Date());
        staffList = new ArrayList<>();
        staffList.add(staff);
        staffList.add(staff2);
        staffDTO = new StaffDTO((byte) 1, "firstName", "lastName", new Address(), new Byte[1], "email", (byte) 1, true, "username", "password", new Date());
        staffDTO2 = new StaffDTO((byte) 2, "firstName", "lastName", new Address(), new Byte[1], "email", (byte) 1, true, "username", "password", new Date());
        staffDTOList = new ArrayList<>();
        staffDTOList.add(staffDTO);
        staffDTOList.add(staffDTO2);

    }

    @AfterEach
    void tearDown() {
        reset(staffService);
    }

    @Test
    void findAll() throws Exception {
        given(staffService.findAll()).willReturn(staffList);
        mockMvc.perform(get("/staff"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        then(staffService).should().findAll();
    }

    @Test
    void findById() throws Exception {
        given(mapper.toStaffDTO(staffService.findById(staffDTO.getStaffId()))).willReturn(staffDTO);
        mockMvc.perform(get("/staff/" + staffDTO.getStaffId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("firstName")));

    }

    @Test
    void saveNewStaff() throws Exception {
        MvcResult result = mockMvc.perform(post("/staff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(staffDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        StaffDTO staffFromContent = mapFromJson(content, StaffDTO.class);
        assertEquals(staffDTO.getFirstName(), staffFromContent.getFirstName());
    }

    @Test
    void updateStaff() throws Exception {
        MvcResult result = mockMvc.perform(put("/staff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(staffDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        StaffDTO staffFromContent = mapFromJson(content, StaffDTO.class);
        assertEquals(staffDTO.getFirstName(), staffFromContent.getFirstName());
    }

    @Test
    void deleteStaff() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/staff/" + staff.getStaffId()))
                .andExpect(status().isOk())
                .andReturn();
        then(staffService).should().deleteById(staff.getStaffId());
        assertEquals("Staff member with id " + staff.getStaffId() + " deleted", mvcResult.getResponse().getContentAsString());
    }
}