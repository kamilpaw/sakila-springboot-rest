package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.service.StoreService;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.StoreDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StoreController {

    private StoreService storeService;
    private DTOMapper mapper;

    public StoreController(StoreService storeService, DTOMapper mapper){
        this.storeService = storeService;
        this.mapper = mapper;
    }

    @GetMapping("/stores")
    public List<StoreDTO> findAll(){
        return storeService.findAll().stream().map(mapper::toStoreDTO).collect(Collectors.toList());
    }

    @GetMapping("/stores/{storeId}")
    public StoreDTO findById(@PathVariable Byte storeId){
        return mapper.toStoreDTO(storeService.finById(storeId));
    }

    @PostMapping("/stores")
    public StoreDTO saveNewStore(@RequestBody StoreDTO storeDTO){
        storeDTO.setStoreId((byte) 0);
        storeService.save(mapper.toStore(storeDTO));
        return storeDTO;
    }

    @PutMapping("/stores")
    public StoreDTO updateStore(@RequestBody StoreDTO storeDTO){
        storeService.save(mapper.toStore(storeDTO));
        return storeDTO;
    }

    @DeleteMapping("/stores/{storeID}")
    public String deleteById(@PathVariable byte storeID){
        storeService.deleteById(storeID);
        return "Store with id " + storeID + " deleted";
    }

}
