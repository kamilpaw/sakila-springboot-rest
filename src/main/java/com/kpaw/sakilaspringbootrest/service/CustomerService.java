package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.web.model.dtos.CustomerDTO;
import com.kpaw.sakilaspringbootrest.web.model.pages.CustomerPagedList;
import org.springframework.data.domain.PageRequest;

public interface CustomerService {

    CustomerPagedList findAll(PageRequest pageRequest);

    CustomerDTO findById(short id);

    void save(CustomerDTO customerDTO);

    void deleteById(short id);

    CustomerPagedList findCustomerByFirstNameOrLastName(String name, PageRequest pageRequest);
}
