package com.example.cinema.controllers;

import com.example.cinema.domain.Ticket;
import com.example.cinema.service.TicketService;
import com.example.cinema.service.TicketServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")

public class TicketController {

    private final TicketService ticketService;
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    @GetMapping("/{id}")
    public List<Ticket> getTickets(@PathVariable("id") Long id){
        return ticketService.getAllTicketsForSession(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTicket(@PathVariable("id") Long id){
        ticketService.cancelTicket(id);
        return true;
    }

    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.CREATED)
    public Long buyTicket (@RequestBody Ticket ticket){
       // ticketService.newTicket();
        return ticket.getId();
    }



}
