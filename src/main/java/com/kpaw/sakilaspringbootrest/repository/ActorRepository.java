package com.kpaw.sakilaspringbootrest.repository;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Short> {

    @Query(value = "SELECT * FROM actor WHERE actor_id IN (SELECT actor_id FROM film_actor WHERE film_id = ?1)", nativeQuery = true)
    List<Actor> findActorsByFilmId(int id);
}
