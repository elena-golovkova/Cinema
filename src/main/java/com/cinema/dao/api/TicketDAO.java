package com.cinema.dao.api;

import com.cinema.exception.TicketPurchaseException;
import com.cinema.model.Ticket;

import java.util.List;

public interface TicketDAO<Ticket, Integer> extends Dao<Ticket, Integer> {

    void purchaseTicket(int row, int column, int sessionId) throws TicketPurchaseException;

    void returnTicket(Integer id);

    List<Ticket> getAllSoldTicketsFromSession(Integer id);

    void checkTicket(int row, int column, int sessionId) throws TicketPurchaseException;

}
