package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.location.Inventory;

import java.util.List;

public interface InventoryService {

    List<Inventory> findAll();

    Inventory findById(int id);

    void save(Inventory inventory);

    void delete(int id);
}
