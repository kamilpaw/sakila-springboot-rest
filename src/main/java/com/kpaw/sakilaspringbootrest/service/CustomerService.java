package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.rent.Customer;
import com.kpaw.sakilaspringbootrest.web.model.CustomerDTO;
import com.kpaw.sakilaspringbootrest.web.model.CustomerPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CustomerService {

    CustomerPagedList findAll(PageRequest pageRequest);

    CustomerDTO findById(short id);

    void save(CustomerDTO customerDTO);

    void deleteById(short id);

    CustomerPagedList findCustomerByFirstNameOrLastName(String name, PageRequest pageRequest);
}
