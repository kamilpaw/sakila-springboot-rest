package com.kpaw.sakilaspringbootrest.web.controller;


import com.kpaw.sakilaspringbootrest.domain.rent.Customer;
import com.kpaw.sakilaspringbootrest.service.CustomerService;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.CustomerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private CustomerService customerService;
    private DTOMapper mapper;

    public CustomerController(CustomerService customerService, DTOMapper mapper){
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @GetMapping("/customers")
    public List<CustomerDTO> findAll(){
        return customerService.findAll().stream().map(mapper::toCustomerDTO).collect(Collectors.toList());
    }

    @GetMapping("/customers/{customerId}")
    public CustomerDTO findById(@PathVariable short customerId){
        return mapper.toCustomerDTO(customerService.findById(customerId));
    }

    @PostMapping("/customers")
    public String saveNewCustomer(@RequestBody CustomerDTO customerDTO){
        customerDTO.setCustomerId((short) 0);
        Customer customer = mapper.toCustomer(customerDTO);
        customerService.save(customer);
        return "Saved new customer: " + customer.toString();
    }

    @PutMapping("/customers")
    public String updateCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = mapper.toCustomer(customerDTO);
        customerService.save(customer);
        return "Updated customer: " + customer.toString();
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable short customerId){
        customerService.deleteById(customerId);
        return "Customer with id " + customerId + " deleted";
    }
}
