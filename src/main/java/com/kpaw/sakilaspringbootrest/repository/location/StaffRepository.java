package com.kpaw.sakilaspringbootrest.repository.location;

import com.kpaw.sakilaspringbootrest.domain.location.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="employees")
public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
