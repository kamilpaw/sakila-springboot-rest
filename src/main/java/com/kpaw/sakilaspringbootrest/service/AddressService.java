package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.domain.location.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    Address findById(int id);

    void save(Address address);
}
