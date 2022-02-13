package com.kpaw.sakilaspringbootrest.repository.rent;

import com.kpaw.sakilaspringbootrest.domain.location.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Byte> {
}