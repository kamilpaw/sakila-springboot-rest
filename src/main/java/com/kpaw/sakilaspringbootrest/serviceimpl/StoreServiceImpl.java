package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.location.Store;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.rental.StoreRepository;
import com.kpaw.sakilaspringbootrest.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    StoreRepository storeRepository;

    @Autowired
    StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


    @Override
    public Store finById(byte id) {
        Optional<Store> result = storeRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundExc("Store", id);
        }
        return result.get();
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public void save(Store store) {
        storeRepository.save(store);
    }

    @Override
    public void deleteById(byte id) {
        storeRepository.deleteById(id);
    }
}

