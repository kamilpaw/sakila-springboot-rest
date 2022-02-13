package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.service.RentalService;
import com.kpaw.sakilaspringbootrest.web.model.dtos.RentalDTO;
import com.kpaw.sakilaspringbootrest.web.model.pages.RentalPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.kpaw.sakilaspringbootrest.web.model.pages.PageSizeAndNumber.pageNumber;
import static com.kpaw.sakilaspringbootrest.web.model.pages.PageSizeAndNumber.pageSize;

@RestController
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/rentals")
    public RentalPagedList findAll(@RequestParam(required = false) Integer pageNumber,
                                   @RequestParam(required = false) Integer pageSize) {
        return rentalService.findAll(PageRequest.of(pageNumber(pageNumber), pageSize(pageSize)));
    }

    @GetMapping("/rentals/{rentalId}")
    public RentalDTO findById(@PathVariable int rentalId) {
        return rentalService.findById(rentalId);
    }

    @PostMapping("/rentals")
    public RentalDTO saveNewRental(@RequestBody RentalDTO rentalDTO) {
        rentalDTO.setRentalId(null);
        rentalService.save(rentalDTO);
        return rentalDTO;
    }

    @PutMapping("/rentals")
    public RentalDTO updateRental(@RequestBody RentalDTO rentalDTO) {
        rentalService.save(rentalDTO);
        return rentalDTO;
    }

    @DeleteMapping("/rentals/{rentalId}")
    public String deleteRental(@PathVariable int rentalId) {
        rentalService.deleteById(rentalId);
        return "rental with id " + rentalId + " deleted";
    }

    @GetMapping("/rentals/customers/{customerId}")
    public RentalPagedList findRentalsByCustomerID(@PathVariable int customerId,
                                                   @RequestParam(required = false) Integer pageNumber,
                                                   @RequestParam(required = false) Integer pageSize) {
        return rentalService.findRentalsByCustomerId(customerId, PageRequest.of(pageNumber(pageNumber), pageSize(pageSize)));
    }
}
