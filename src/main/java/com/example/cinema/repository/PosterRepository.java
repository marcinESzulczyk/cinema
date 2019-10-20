package com.example.cinema.repository;

import com.example.cinema.domain.Movie;
import com.example.cinema.domain.Poster;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PosterRepository extends CrudRepository<Poster,Long> {

    Optional<Poster>findByMovie(Movie movie);
}
