package com.kpaw.sakilaspringbootrest.repository.location;

import com.kpaw.sakilaspringbootrest.domain.location.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Byte> {
}
