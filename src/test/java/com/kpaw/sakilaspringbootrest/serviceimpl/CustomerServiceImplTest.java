package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.rent.Customer;
import com.kpaw.sakilaspringbootrest.repository.rental.CustomerRepository;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.CustomerDTO;
import com.kpaw.sakilaspringbootrest.web.model.pages.CustomerPagedList;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
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
    CustomerPagedList pagedList;
    Page<Customer> page;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer1 = new Customer();
        customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer1);
        pagedList = new CustomerPagedList(customers.stream().map(mapper::toCustomerDTO).collect(Collectors.toList()),PageRequest.of(1,1),2);
        page = new Page<Customer>() {
            @Override
            public int getTotalPages() {
                return 1;
            }

            @Override
            public long getTotalElements() {
                return 2;
            }

            @Override
            public <U> Page<U> map(Function<? super Customer, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 1;
            }

            @Override
            public int getSize() {
                return 2;
            }

            @Override
            public int getNumberOfElements() {
                return 2;
            }

            @Override
            public List<Customer> getContent() {
                return customers;
            }

            @Override
            public boolean hasContent() {
                return true;
            }

            @Override
            public Sort getSort() {
                return Sort.unsorted();
            }

            @Override
            public boolean isFirst() {
                return true;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<Customer> iterator() {
                return null;
            }
        };
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