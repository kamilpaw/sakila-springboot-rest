package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.rent.Rental;
import com.kpaw.sakilaspringbootrest.repository.rental.RentalRepository;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.RentalDTO;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class RentalServiceImplTest {

    @Mock
    RentalRepository rentalRepository;

    @Mock
    DTOMapper mapper;

    @InjectMocks
    RentalServiceImpl service;

    @Captor
    ArgumentCaptor<PageRequest> captor;

    Rental rental, rental1;
    RentalDTO dto;
    List<Rental> list;
    Page<Rental> page;

    @BeforeEach
    void setUp() {
        rental = new Rental();
        rental1 = new Rental();
        dto = new RentalDTO();
        list = new ArrayList<>();
        list.add(rental);
        list.add(rental1);
        page = new SetUpPage<>(list);
    }

    @Test
    void findAll() {
        given(rentalRepository.findAll(captor.capture())).willReturn(page);
        service.findAll(captor.capture());
        then(rentalRepository).should().findAll(captor.capture());
        assertEquals(2, rentalRepository.findAll(captor.capture()).getContent().size());
    }

    @Test
    void findById() {
        given(rentalRepository.findById(anyInt())).willReturn(Optional.of(rental));
        service.findById(anyInt());
        then(rentalRepository).should().findById(anyInt());
        assertThat(rentalRepository.findById(anyInt())).isNotEmpty();
    }

    @Test
    void save() {
        service.save(dto);
        then(rentalRepository).should().save(mapper.toRental(dto));
    }

    @Test
    void deleteById() {
        service.deleteById(1);
        then(rentalRepository).should().deleteById(1);
    }

    @Test
    void findRentalsByCustomerId() {
        given(rentalRepository.findRentalsByCustomerId(anyInt(),captor.capture())).willReturn(page);
        service.findRentalsByCustomerId(anyInt(),captor.capture());
        then(rentalRepository).should().findRentalsByCustomerId(anyInt(), captor.capture());
        assertEquals(2, rentalRepository.findRentalsByCustomerId(anyInt(), captor.capture()).getContent().size());
    }
}