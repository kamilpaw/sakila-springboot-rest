package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.web.model.dtos.RentalDTO;
import com.kpaw.sakilaspringbootrest.web.model.pages.RentalPagedList;
import org.springframework.data.domain.PageRequest;

public interface RentalService {

    RentalPagedList findAll(PageRequest pageRequest);

    RentalDTO findById(int id);

    void save(RentalDTO rentalDTO);

    void deleteById(int id);

    RentalPagedList findRentalsByCustomerId(int customerId, PageRequest pageRequest);
}
