package com.cinema.dao.api;

import com.cinema.model.Ticket;

import java.util.List;

public interface TicketDAO<Ticket, Integer> extends Dao<Ticket, Integer> {

    void purchaseTicket(int row, int column, int sessionId);
    void returnTicket(Integer id);
    public List<Ticket> getAllSoldTicketFromSession(Integer id);

}
