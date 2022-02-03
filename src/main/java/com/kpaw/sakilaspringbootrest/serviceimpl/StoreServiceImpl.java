package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.location.Store;
import com.kpaw.sakilaspringbootrest.repository.StoreRepository;
import com.kpaw.sakilaspringbootrest.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    StoreRepository storeRepository;

    @Autowired
    StoreServiceImpl(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    @Override
    public Store finById(byte id) {
        Optional<Store> result = storeRepository.findById(id);
        //exception when result not present;
        return result.get();
    }
}
