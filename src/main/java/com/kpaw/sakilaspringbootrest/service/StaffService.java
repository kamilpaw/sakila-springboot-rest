package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.location.Staff;

import java.util.List;

public interface StaffService {

    List<Staff> findAll();

    Staff findById(Byte id);

    void save(Staff staff);

    void deleteById(Byte id);
}
