package com.example.cinema.service;

import com.example.cinema.domain.Marathon;
import com.example.cinema.domain.Movie;
import com.example.cinema.exception.EntityDoesNotExistsException;
import com.example.cinema.repository.MarathonRepository;
import com.example.cinema.repository.MovieRepository;
import com.example.cinema.utils.IterableUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MarathonServiceImpl implements MarathonService {

    private MarathonRepository marathonRepository;
    private MovieRepository movieRepository;

    public MarathonServiceImpl(MarathonRepository marathonRepository, MovieRepository movieRepository) {
        this.marathonRepository = marathonRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public Long createMarathon(String name, LocalDateTime startTime, List<Long>movieIds){
        List<Movie>movies = new ArrayList<>();
        for (Long movieId:movieIds){
            Optional<Movie>movieOptional=movieRepository.findById(movieId);
            if(!movieOptional.isPresent()){
                throw new EntityDoesNotExistsException("Movie, id="+movieId);
            }
            movies.add(movieOptional.get());
        }

        Marathon marathon = new Marathon(null, name, startTime);
        marathon.setMovies(movies);

        for(Movie movie:movies){
            movie.getMarathons().add(marathon);
        }

        marathonRepository.save(marathon);

        return marathon.getId();
    }

    @Override
    public Optional<Marathon>getMarathon(Long marathonId){return marathonRepository.findById(marathonId);}

    @Override
    public List<Marathon>getAllMarathons(){return IterableUtils.iterableToList(marathonRepository.findAll());
    }

    @Override
    @Transactional
    public void removeMarathon(Long marathonId){marathonRepository.deleteById(marathonId);}

}
