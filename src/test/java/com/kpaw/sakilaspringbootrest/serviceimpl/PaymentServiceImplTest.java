package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.rent.Payment;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.rental.PaymentRepository;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.PaymentDTO;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    DTOMapper mapper;

    @InjectMocks
    PaymentServiceImpl service;

    @Captor
    ArgumentCaptor<PageRequest> captor;

    Payment payment, payment1;
    PaymentDTO dto;
    List<Payment> paymentList;
    Page<Payment> page;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        payment1 = new Payment();
        dto = new PaymentDTO();
        paymentList = new ArrayList<>();
        paymentList.add(payment);
        paymentList.add(payment1);
        page = new SetUpPage<>(paymentList);
    }

    @Test
    void findAll() {
        given(paymentRepository.findAll(captor.capture())).willReturn(page);
        service.findAll(captor.capture());
        then(paymentRepository).should().findAll(captor.capture());
        assertEquals(2, paymentRepository.findAll(captor.capture()).getContent().size());
    }

    @Test
    void save() {
        service.save(dto);
        then(paymentRepository).should().save(mapper.toPayment(dto));
    }

    @Test
    void findById() {
        given(paymentRepository.findById((short) 1)).willReturn(Optional.of(payment));
        service.findById((short) 1);
        then(paymentRepository).should().findById((short) 1);
        assertThat(paymentRepository.findById((short)1)).isNotEmpty();
    }

    @Test
    void findByIdNotFound(){
        Optional<Payment> result = paymentRepository.findById((short) 1);
        if(result.isEmpty()){
            assertThrows(EntityNotFoundExc.class, ()-> service.findById((short) 1));
        }
    }

    @Test
    void deleteById() {
        service.deleteById(1);
        then(paymentRepository).should().deleteById((short)1);
    }
}