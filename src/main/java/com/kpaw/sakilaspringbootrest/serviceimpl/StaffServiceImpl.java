package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.location.Staff;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.location.StaffRepository;
import com.kpaw.sakilaspringbootrest.service.StaffService;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    private StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff findById(Byte id) {
        Optional<Staff> result = staffRepository.findById(id);
        if(!result.isPresent()){
            throw new EntityNotFoundExc("Staff", id);
        }
        return result.get();
    }

    @Override
    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void deleteById(Byte id) {
        staffRepository.deleteById(id);
    }
}
