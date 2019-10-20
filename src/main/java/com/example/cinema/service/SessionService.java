package com.example.cinema.service;

import com.example.cinema.domain.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SessionService {

    Long createSession(Long movieId, Long roomId, LocalDateTime startTime);
    Optional<Session>getSession(Long sessionId);
    Optional<Session>getSessionWithTickets(Long sessionId);
    List<Session>getSessionInDate(LocalDate date);
    void removeSession(Long sessionId);
}
