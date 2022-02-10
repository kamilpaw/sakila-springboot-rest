package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.location.Inventory;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.InventoryRepository;
import com.kpaw.sakilaspringbootrest.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory findById(int id) {
        Optional<Inventory> result = inventoryRepository.findById(id);
        if (!result.isPresent()) {
            throw new EntityNotFoundExc("Inventory", id);
        }

        return result.get();
    }

    @Override
    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    @Override
    public void delete(int id) {
        inventoryRepository.deleteById(id);
    }
}
