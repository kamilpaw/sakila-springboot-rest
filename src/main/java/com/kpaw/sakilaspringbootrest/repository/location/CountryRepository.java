package com.kpaw.sakilaspringbootrest.repository.location;

import com.kpaw.sakilaspringbootrest.domain.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="countries")
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
