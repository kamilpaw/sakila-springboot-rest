package com.kpaw.sakilaspringbootrest.repository.location;

import com.kpaw.sakilaspringbootrest.domain.location.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
