package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.rent.Customer;
import com.kpaw.sakilaspringbootrest.repository.rental.CustomerRepository;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.CustomerDTO;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    DTOMapper mapper;

    @InjectMocks
    CustomerServiceImpl service;

    @Captor
    ArgumentCaptor<PageRequest> captor;

    Customer customer, customer1;
    CustomerDTO dto;
    List<Customer> customers;
    Page<Customer> page;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer1 = new Customer();
        customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer1);
        page = new SetUpPage<>(customers);
        dto = new CustomerDTO();
    }

    @Test
    void findAll() {
        given(customerRepository.findAll(captor.capture())).willReturn(page);
        service.findAll(captor.capture());
        then(customerRepository).should().findAll(captor.capture());
        assertEquals(2, customerRepository.findAll(captor.capture()).getContent().size());
    }

    @Test
    void findById() {
        given(customerRepository.findById((short) 1)).willReturn(Optional.of(customer));
        service.findById((short) 1);
        then(customerRepository).should().findById((short)1);
        assertThat(customerRepository.findById((short) 1)).isNotEmpty();

    }

    @Test
    void save() {
        service.save(dto);
        then(customerRepository).should().save(mapper.toCustomer(dto));
    }

    @Test
    void deleteById() {
        service.deleteById((short) 1);
        then(customerRepository).should().deleteById((short) 1);
    }

}