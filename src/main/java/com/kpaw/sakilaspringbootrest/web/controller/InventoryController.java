package com.kpaw.sakilaspringbootrest.web.controller;


import com.kpaw.sakilaspringbootrest.domain.location.Inventory;
import com.kpaw.sakilaspringbootrest.service.InventoryService;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.InventoryDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class InventoryController {

    private InventoryService inventoryService;
    private DTOMapper mapper;

    public InventoryController(InventoryService inventoryService, DTOMapper mapper) {
        this.inventoryService = inventoryService;
        this.mapper = mapper;
    }

    @GetMapping("/inventory")
    public List<InventoryDTO> findAll() {
        return inventoryService.findAll().stream().map(mapper::toInventoryDto).collect(Collectors.toList());
    }

    @GetMapping("/inventory/{inventoryId}")
    public InventoryDTO findById(@PathVariable int inventoryId) {
        return mapper.toInventoryDto(inventoryService.findById(inventoryId));
    }

    @PostMapping("/inventory")
    public String saveNewInventory(@RequestBody InventoryDTO inventoryDTO) {
        inventoryDTO.setInventoryId(0);
        Inventory inventory = mapper.toInventory(inventoryDTO);
        inventoryService.save(inventory);
        return "Saved new inventory: " + inventory.toString();
    }

    @DeleteMapping("/inventory/{inventoryId}")
    public String deleteById(@PathVariable int inventoryId){
        inventoryService.delete(inventoryId);
        return "inventory with id " + inventoryId + " deleted";
    }
}
