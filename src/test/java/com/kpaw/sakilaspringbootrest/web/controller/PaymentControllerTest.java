package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.service.PaymentService;
import com.kpaw.sakilaspringbootrest.web.model.dtos.CustomerDTO;
import com.kpaw.sakilaspringbootrest.web.model.dtos.PaymentDTO;
import com.kpaw.sakilaspringbootrest.web.model.dtos.RentalDTO;
import com.kpaw.sakilaspringbootrest.web.model.pages.PaymentPagedList;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest extends ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PaymentService paymentService;

    @Captor
    ArgumentCaptor<PageRequest> pageRequestArgumentCaptor;

    PaymentDTO paymentDTO;
    PaymentDTO paymentDTO2;
    List<PaymentDTO> paymentDTOList;
    PaymentPagedList paymentPagedList;

    @BeforeEach
    void setUp() {
        CustomerDTO customer = new CustomerDTO();
        RentalDTO rental = new RentalDTO();
        paymentDTO = new PaymentDTO((short) 1, customer, (byte) 1, rental, new BigDecimal("1"), new Timestamp(1), new Date(1));
        paymentDTO2 = new PaymentDTO((short) 2, customer, (byte) 1, rental, new BigDecimal("1"), new Timestamp(1), new Date(1));
        paymentDTOList = new ArrayList<>();
        paymentDTOList.add(paymentDTO);
        paymentDTOList.add(paymentDTO2);
        paymentPagedList = new PaymentPagedList(paymentDTOList, PageRequest.of(1, 1), 2);
    }

    @AfterEach
    void tearDown() {
        reset(paymentService);
    }

    @Test
    void findAll() throws Exception {
        given(paymentService.findAll(pageRequestArgumentCaptor.capture())).willReturn(paymentPagedList);
        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andReturn();
    }

    @Test
    void findById() throws Exception {
        given(paymentService.findById(anyInt())).willReturn(paymentDTO);
        MvcResult mvcResult = mockMvc.perform(get("/payments/" + paymentDTO.getPaymentId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        PaymentDTO payment = mapFromJson(content, PaymentDTO.class);
        assertEquals(paymentDTO.getPaymentId(), payment.getPaymentId());
    }

    @Test
    void saveNewPayment() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/payments")
                        .content(mapToJson(paymentDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        paymentDTO.setPaymentId(null);
        String content = mvcResult.getResponse().getContentAsString();
        PaymentDTO payment = mapFromJson(content, PaymentDTO.class);
        assertEquals(paymentDTO.getPaymentId(), payment.getPaymentId());
    }

    @Test
    void updatePayment() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/payments")
                        .content(mapToJson(paymentDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        PaymentDTO payment = mapFromJson(content, PaymentDTO.class);
        assertEquals(paymentDTO.getPaymentId(), payment.getPaymentId());
    }

    @Test
    void deleteById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/payments/" + paymentDTO.getPaymentId()))
                .andExpect(status().isOk())
                .andReturn();
        then(paymentService).should().deleteById(paymentDTO.getPaymentId());
        assertEquals("Payment with id " + paymentDTO.getPaymentId() + " deleted", mvcResult.getResponse().getContentAsString());
    }
}