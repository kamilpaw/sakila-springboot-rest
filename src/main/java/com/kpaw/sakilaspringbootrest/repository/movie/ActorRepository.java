package com.kpaw.sakilaspringbootrest.repository.movie;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActorRepository extends JpaRepository<Actor, Short> {

    @Query(value = "SELECT * FROM actor WHERE actor_id IN (SELECT actor_id FROM film_actor WHERE film_id = ?1)", nativeQuery = true)
    Page<Actor> findActorsByFilmId(int id, Pageable pageable);


    Page<Actor> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String firstName, String lastName, Pageable pageable);
}
