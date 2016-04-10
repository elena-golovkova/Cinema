package com.cinema.dao.api;

import com.cinema.model.Ticket;

import java.util.List;

public interface TicketDAO<Ticket, Integer> extends Dao<Ticket, Integer> {

    Ticket purchaseTicket(int row, int column, int sessionId);
    Ticket returnTicket(int row, int column, int sessionId);

}
