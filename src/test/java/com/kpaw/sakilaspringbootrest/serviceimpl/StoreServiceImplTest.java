package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.location.Store;
import com.kpaw.sakilaspringbootrest.repository.rental.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {

    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    StoreServiceImpl service;

    Store store, store1;
    List<Store> list;

    @BeforeEach
    void setUp() {
        store = new Store();
        store1 = new Store();
        list = new ArrayList<>();
        list.add(store);
        list.add(store1);
    }

    @Test
    void finById() {
        given(storeRepository.findById((byte) 1)).willReturn(Optional.of(store));
        service.finById((byte) 1);
        then(storeRepository).should().findById((byte) 1);
        assertThat(storeRepository.findById((byte) 1)).isNotEmpty();
    }

    @Test
    void findAll() {
        given(storeRepository.findAll()).willReturn(list);
        service.findAll();
        then(storeRepository).should().findAll();
        assertEquals(2, storeRepository.findAll().size());
    }

    @Test
    void save() {
        service.save(store);
        then(storeRepository).should().save(store);
    }

    @Test
    void deleteById() {
        service.deleteById((byte) 1);
        then(storeRepository).should().deleteById((byte)1);
    }
}