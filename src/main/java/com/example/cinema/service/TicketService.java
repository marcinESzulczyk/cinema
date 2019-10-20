package com.example.cinema.service;

import com.example.cinema.domain.Ticket;

import java.math.BigDecimal;
import java.util.List;

public interface TicketService {

    Long newTicket(Long sessionId, String seat, BigDecimal price);

    List<Ticket> getAllTicketsForSession(Long sessionId);

    void cancelTicket(Long ticketId);
}
