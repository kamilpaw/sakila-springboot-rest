package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.location.Inventory;
import com.kpaw.sakilaspringbootrest.domain.location.Store;
import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import com.kpaw.sakilaspringbootrest.repository.location.InventoryRepository;
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
class InventoryServiceImplTest {

    @Mock
    InventoryRepository inventoryRepository;

    @InjectMocks
    InventoryServiceImpl service;

    Inventory inventory, inventory1;
    List<Inventory> inventoryList;

    @BeforeEach
    void setUp() {
        inventory = new Inventory(1, new Film(), new Store());
        inventory1 = new Inventory(2, new Film(), new Store());
        inventoryList = new ArrayList<>();
        inventoryList.add(inventory);
        inventoryList.add(inventory1);
    }

    @Test
    void findAll() {
        given(inventoryRepository.findAll()).willReturn(inventoryList);
        service.findAll();
        then(inventoryRepository).should().findAll();
        assertEquals(2, service.findAll().size());
    }

    @Test
    void findById() {
        given(inventoryRepository.findById(1)).willReturn(Optional.of(inventory));
        Inventory foundInventory = service.findById(1);
        then(inventoryRepository).should().findById(1);
        assertThat(foundInventory).isNotNull();
    }

    @Test
    void save() {
        service.save(inventory);
        then(inventoryRepository).should().save(inventory);
    }

    @Test
    void delete() {
        service.delete(1);
        then(inventoryRepository).should().deleteById(1);
    }
}