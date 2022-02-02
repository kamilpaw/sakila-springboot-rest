package com.kpaw.sakilaspringbootrest.repository;

import com.kpaw.sakilaspringbootrest.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query(value = "SELECT * FROM film WHERE film_id IN " +
            "(SELECT film_id FROM film_actor WHERE actor_id = ?1 )", nativeQuery = true)
    List<Film> findFilmsByActorId(int id);

    @Query(value = "SELECT * FROM film WHERE film_id IN (SELECT film_id FROM film_category WHERE category_id = ?1)", nativeQuery = true)
    List<Film> findFilmsByCategoryId(int id);
}
