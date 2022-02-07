package com.kpaw.sakilaspringbootrest.web.controller;


import com.kpaw.sakilaspringbootrest.domain.location.Staff;
import com.kpaw.sakilaspringbootrest.service.StaffService;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.StaffDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StaffController {

    private DTOMapper mapper;
    private StaffService staffService;

    public StaffController(StaffService staffService, DTOMapper DTOMapper){
        this.staffService = staffService;
        this.mapper = DTOMapper;
    }

    @GetMapping("/staff")
    public List<StaffDTO> findAll(){
        return staffService.findAll().stream().map(mapper::toStaffDTO).collect(Collectors.toList());
    }

    @GetMapping("/staff/{staffId}")
    public StaffDTO findById(@PathVariable byte staffId){
        return mapper.toStaffDTO(staffService.findById(staffId));
    }

    @PostMapping("/staff")
    public String saveNewStaff(@RequestBody StaffDTO staffDTO){
        staffDTO.setStaffId((byte) 0);
        Staff staff = mapper.toStaff(staffDTO);
        staffService.save(staff);
        return "Saved new memmber of staff: " + staff.toString();
    }

    @PutMapping("/staff")
    public String updateStaff(@RequestBody StaffDTO staffDTO){
        Staff staff = mapper.toStaff(staffDTO);
        staffService.save(staff);
        return "Updated memeber of staff: " + staff.toString();
    }

    @DeleteMapping("/staff/{staffId}")
    public String deleteStaff(@PathVariable byte staffId){
        staffService.deleteById(staffId);
        return "Staff member with id " + staffId + " deleted";
    }
}
