package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.location.Store;
import com.kpaw.sakilaspringbootrest.web.model.dtos.StoreDTO;

import java.util.List;

public interface StoreService {

    Store finById(byte id);

    List<Store> findAll();

    void save(Store store);

    void deleteById(byte id);
}
