package com.example.cinema.service;

import com.example.cinema.domain.Movie;
import com.example.cinema.domain.Room;
import com.example.cinema.domain.Session;
import com.example.cinema.exception.EntityDoesNotExistsException;
import com.example.cinema.repository.MovieRepository;
import com.example.cinema.repository.RoomRepository;
import com.example.cinema.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;
    private MovieRepository movieRepository;
    private RoomRepository roomRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, MovieRepository movieRepository, RoomRepository roomRepository) {
        this.sessionRepository = sessionRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public Long createSession(Long movieId, Long roomId, LocalDateTime startTime){
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if(!movieOptional.isPresent()){
            throw new EntityDoesNotExistsException("Movie, id="+movieId);
        }

        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if(!roomOptional.isPresent()){
            throw new EntityDoesNotExistsException("Room, id="+roomId);
        }

        Session session = new Session(null, startTime);
        session.setRoom(roomOptional.get());
        session.setMovie(movieOptional.get());

        sessionRepository.save(session);

        return session.getId();
    }

    @Override
    public Optional<Session> getSession(Long sessionId){return sessionRepository.findById(sessionId);}

    @Override
    public Optional<Session>getSessionWithTickets(Long sessionId){return sessionRepository.readById(sessionId);}

    @Override
    public List<Session>getSessionInDate(LocalDate date){
        return sessionRepository.findAllByStartDate(Date.valueOf(date));
    }

    @Override
    @Transactional
    public void removeSession(Long sessionId){sessionRepository.deleteById(sessionId);}

}
