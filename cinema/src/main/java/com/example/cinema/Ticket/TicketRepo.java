package com.example.cinema.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket,Long> {
}