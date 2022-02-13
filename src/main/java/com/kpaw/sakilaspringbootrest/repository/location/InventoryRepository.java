package com.kpaw.sakilaspringbootrest.repository.location;

import com.kpaw.sakilaspringbootrest.domain.location.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}
