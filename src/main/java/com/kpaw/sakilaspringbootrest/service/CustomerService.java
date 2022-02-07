package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.rent.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    Customer findById(short id);
    void save(Customer customer);
    void deleteById(short id);
}
