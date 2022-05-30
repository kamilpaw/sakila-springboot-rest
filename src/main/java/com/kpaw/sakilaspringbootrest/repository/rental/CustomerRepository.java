package com.kpaw.sakilaspringbootrest.repository.rental;

import com.kpaw.sakilaspringbootrest.domain.rent.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

   Page<Customer> findCustomerByFirstNameContainsOrLastNameContainsAllIgnoreCase(String firstName, String lastName, Pageable pageable);
}
