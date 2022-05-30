package com.kpaw.sakilaspringbootrest.repository.rental;

import com.kpaw.sakilaspringbootrest.domain.location.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
