package com.example.cinema.repository;

import com.example.cinema.domain.EMovieCategory;
import com.example.cinema.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

    Page<Movie>findByCategory(EMovieCategory category, Pageable pageable);

    Page<Movie>findByTitleContaining(String partOfTitle, Pageable pageable);
}
