package com.kpaw.sakilaspringbootrest.repository.movie;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query(value = "SELECT * FROM film WHERE film_id IN " +
            "(SELECT film_id FROM film_actor WHERE actor_id = ?1 )", nativeQuery = true)
    Page<Film> findFilmsByActorId(int id, PageRequest pageRequest);

    @Query(value = "SELECT * FROM film WHERE film_id IN (SELECT film_id FROM film_category WHERE category_id = ?1)", nativeQuery = true)
    List<Film> findFilmsByCategoryId(int id);

    Page<Film> findAllByTitle(String title, PageRequest pageRequest);

}
