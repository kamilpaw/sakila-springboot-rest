package com.kpaw.sakilaspringbootrest.web.controller;


import com.kpaw.sakilaspringbootrest.domain.location.Address;
import com.kpaw.sakilaspringbootrest.service.AddressService;
import com.kpaw.sakilaspringbootrest.service.CustomerService;
import com.kpaw.sakilaspringbootrest.web.model.CustomerDTO;
import com.kpaw.sakilaspringbootrest.web.model.CustomerPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.kpaw.sakilaspringbootrest.web.model.PageSizeAndNumber.pageNumber;
import static com.kpaw.sakilaspringbootrest.web.model.PageSizeAndNumber.pageSize;

@RestController
public class CustomerController {

    private CustomerService customerService;
    private AddressService addressService;

    public CustomerController(CustomerService customerService, AddressService addressService) {
        this.customerService = customerService;
        this.addressService = addressService;
    }

    @GetMapping("/customers")
    public CustomerPagedList findAll(@RequestParam(required = false) Integer pageNumber,
                                     @RequestParam(required = false) Integer pageSize) {
        return customerService.findAll(PageRequest.of(pageNumber(pageNumber), pageSize(pageSize)));
    }

    @GetMapping("/customers/{customerId}")
    public CustomerDTO findById(@PathVariable short customerId) {
        return customerService.findById(customerId);
    }

    @PostMapping("/customers")
    public CustomerDTO saveNewCustomer(@RequestBody CustomerDTO customerDTO) {
        customerDTO.setCustomerId(null);
        if (customerDTO.getAddress().getAddressId()==null){
            Address address = customerDTO.getAddress();
            addressService.save(address);
        }
        return customerDTO;
    }

    @PutMapping("/customers")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
        if (customerDTO.getAddress().getAddressId()==null){
            Address address = customerDTO.getAddress();
            addressService.save(address);
        }
        customerService.save(customerDTO);
        return customerDTO;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable short customerId) {
        customerService.deleteById(customerId);
        return "Customer with id " + customerId + " deleted";
    }

    @GetMapping("/customers/search")
    public CustomerPagedList findByFirstNameOrLastName(@RequestParam(required = false) Integer pageNumber,
                                                       @RequestParam(required = false) Integer pageSize,
                                                       @RequestParam(defaultValue = "") String name) {
        return customerService.findCustomerByFirstNameOrLastName(name, PageRequest.of(pageNumber(pageNumber), pageSize(pageSize)));
    }
}
