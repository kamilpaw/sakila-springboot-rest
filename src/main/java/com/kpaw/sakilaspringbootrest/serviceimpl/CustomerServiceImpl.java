package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.rent.Customer;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.rental.CustomerRepository;
import com.kpaw.sakilaspringbootrest.service.CustomerService;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.CustomerDTO;
import com.kpaw.sakilaspringbootrest.web.model.pages.CustomerPagedList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final DTOMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, DTOMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public CustomerPagedList findAll(PageRequest pageRequest) {
        Page<Customer> customerPage = customerRepository.findAll(pageRequest);
        return new CustomerPagedList(customerPage.getContent().stream().map(mapper::toCustomerDTO).collect(Collectors.toList()),
                PageRequest.of(customerPage.getPageable().getPageNumber(), customerPage.getPageable().getPageSize()),
                customerPage.getTotalElements());

    }

    @Override
    public CustomerDTO findById(short id) {
        Optional<Customer> result = customerRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundExc("Customer", id);
        }
        return mapper.toCustomerDTO(result.get());
    }

    @Override
    public void save(CustomerDTO customerDTO) {
        customerRepository.save(mapper.toCustomer(customerDTO));
    }

    @Override
    public void deleteById(short id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerPagedList findCustomerByFirstNameOrLastName(String name, PageRequest pageRequest) {
        Page<Customer> customerPage = customerRepository.findCustomerByFirstNameContainsOrLastNameContainsAllIgnoreCase(name, name, pageRequest);
        return new CustomerPagedList(customerPage.getContent().stream().map(mapper::toCustomerDTO).collect(Collectors.toList()),
                PageRequest.of(customerPage.getPageable().getPageNumber(), customerPage.getPageable().getPageSize()),
                customerPage.getTotalElements());
    }

}
