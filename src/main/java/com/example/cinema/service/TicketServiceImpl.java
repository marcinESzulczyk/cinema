package com.example.cinema.service;

import com.example.cinema.domain.Session;
import com.example.cinema.domain.Ticket;
import com.example.cinema.exception.BusinessException;
import com.example.cinema.exception.EntityDoesNotExistsException;
import com.example.cinema.repository.SessionRepository;
import com.example.cinema.repository.TicketRepository;
import com.example.cinema.utils.IterableUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TicketServiceImpl implements TicketService{

    private TicketRepository ticketRepository;
    private SessionRepository sessionRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, SessionRepository sessionRepository) {
        this.ticketRepository = ticketRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    @Transactional
    public Long newTicket(Long sessionId, String seat, BigDecimal price){
        Optional<Session>sessionOptional=sessionRepository.findById(sessionId);
        if (!sessionOptional.isPresent()){
            throw new EntityDoesNotExistsException("Session, id="+sessionId);
        }

        Long amountOfTicketsInSession = ticketRepository.countBySession(sessionOptional.get());
        if (amountOfTicketsInSession==sessionOptional.get().getRoom().getCapacity()){
            throw new BusinessException("All tickets have been sold out, sessionId="+sessionId);
        }

        Ticket ticket= new Ticket(null, seat,price);

        sessionOptional.get().addTicket(ticket);

        ticketRepository.save(ticket);

        return ticket.getId();

    }

    @Override
    
    public List<Ticket>getAllTicketsForSession(Long sessionId){
        Optional<Session>sessionOptional=sessionRepository.findById(sessionId);
        if(!sessionOptional.isPresent()){
            throw new EntityDoesNotExistsException("Session, id="+sessionId);
        }

        Iterable<Ticket>tickets=ticketRepository.findAllBySession(sessionOptional.get());

        return IterableUtils.iterableToList(tickets);
    }

    @Override
    @Transactional
    public void cancelTicket(Long ticketId){ticketRepository.deleteById(ticketId);}
}
