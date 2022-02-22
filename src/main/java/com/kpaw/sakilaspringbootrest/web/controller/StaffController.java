package com.kpaw.sakilaspringbootrest.web.controller;


import com.kpaw.sakilaspringbootrest.service.StaffService;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.StaffDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StaffController {

    private final DTOMapper mapper;
    private final StaffService staffService;

    public StaffController(StaffService staffService, DTOMapper DTOMapper){
        this.staffService = staffService;
        this.mapper = DTOMapper;
    }

    @GetMapping("/staff")
    public List<StaffDTO> findAll(){
        return staffService.findAll().stream().map(mapper::toStaffDTO).collect(Collectors.toList());
    }

    @GetMapping("/staff/{staffId}")
    public StaffDTO findById(@PathVariable Byte staffId){
        return mapper.toStaffDTO(staffService.findById(staffId));
    }

    @PostMapping("/staff")
    public StaffDTO saveNewStaff(@RequestBody StaffDTO staffDTO){
        staffDTO.setStaffId((byte) 0);
        staffService.save(mapper.toStaff(staffDTO));
        return staffDTO;
    }

    @PutMapping("/staff")
    public StaffDTO updateStaff(@RequestBody StaffDTO staffDTO){
        staffService.save(mapper.toStaff(staffDTO));
        return staffDTO;
    }

    @DeleteMapping("/staff/{staffId}")
    public String deleteStaff(@PathVariable Byte staffId){
        staffService.deleteById(staffId);
        return "Staff member with id " + staffId + " deleted";
    }
}
