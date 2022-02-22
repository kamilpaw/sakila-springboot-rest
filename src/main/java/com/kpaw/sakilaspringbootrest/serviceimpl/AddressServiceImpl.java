package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.location.Address;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.location.AddressRepository;
import com.kpaw.sakilaspringbootrest.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(int id) {
        Optional<Address> result = addressRepository.findById(id);
        if (result.isEmpty()){
           throw new EntityNotFoundExc("Address", id);
        }
        return result.get();
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }
}
