package com.example.cinema.repository;

import com.example.cinema.domain.Session;
import com.example.cinema.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    Iterable<Ticket>findAllBySession(Session session);

    Long countBySession(Session session);
}
