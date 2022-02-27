package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.location.Address;
import com.kpaw.sakilaspringbootrest.repository.location.AddressRepository;
import com.kpaw.sakilaspringbootrest.repository.movie.ActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    AddressServiceImpl service;

    Address address, address1;
    List<Address> addressList;



    @BeforeEach
    void setUp() {
        address = new Address();
        address1 = new Address();
        addressList = new ArrayList<>();
        addressList.add(address);
        addressList.add(address1);
    }

    @Test
    void findAll() {
        given(addressRepository.findAll()).willReturn(addressList);
        service.findAll();
        then(addressRepository).should().findAll();
        assertEquals(2, addressRepository.findAll().size());
    }

    @Test
    void findById() {
        given(addressRepository.findById(1)).willReturn(Optional.of(address));
        Address foundAddress = service.findById(1);
        then(addressRepository).should().findById(1);
        assertEquals(foundAddress.toString(), addressRepository.findById(1).get().toString());
    }

    @Test
    void save() {
        service.save(address);
        then(addressRepository).should().save(address);
    }
}