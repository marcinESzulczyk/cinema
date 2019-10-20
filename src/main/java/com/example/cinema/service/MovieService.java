package com.example.cinema.service;

import com.example.cinema.domain.EMovieCategory;
import com.example.cinema.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.EmptyStackException;
import java.util.Optional;

public interface MovieService {

    Long createMovie(String title, EMovieCategory category, Integer lenght, String description, Integer requiredAge, String posterFilePath);

    Optional<Movie> getMovie(Long movieId);

    Page<Movie>getMoviesInCategory(EMovieCategory category, Pageable pageable);

    Page<Movie>getMoviesByPartOfTitle(String partOfTitle, Pageable pageable);

    Page<Movie>getAllMovies(Pageable pageable);

    void updateMovie(Long movieId, String title, EMovieCategory category, Integer lenght, String description, Integer requiredAge, String posterFilePath);

    void removeMovie(Long movieId);
}
