package com.kpaw.sakilaspringbootrest.repository.rental;

import com.kpaw.sakilaspringbootrest.domain.rent.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query(value = "SELECT * FROM rental WHERE customer_id = ?1", nativeQuery = true)
    Page<Rental> findRentalsByCustomerId(int customerId, Pageable pageable);
}
