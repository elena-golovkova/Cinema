package com.cinema.dao.impl;

import com.cinema.dao.api.TicketDAO;
import com.cinema.dao.storage.InMemoryDB;
import com.cinema.exception.TicketPurchaseException;
import com.cinema.model.Ticket;

import java.util.List;

public final class TicketDAOImpl extends AbstractDAOInMemory<Ticket, Integer> implements TicketDAO<Ticket, Integer> {
    private static TicketDAOImpl ticketDAO;

    public synchronized static TicketDAOImpl getInstance() {

        if (ticketDAO == null) {
            ticketDAO = new TicketDAOImpl(Ticket.class);

        }
        return ticketDAO;
    }

    private TicketDAOImpl(Class type) {
        super(type);
    }

    @Override
    public void purchaseTicket(int row, int column, int sessionId) {
        InMemoryDB instance = InMemoryDB.getInstance();
        instance.purchaseTicket(row, column, sessionId);
    }

    @Override
    public void returnTicket(Integer id) {
        InMemoryDB instance = InMemoryDB.getInstance();
        instance.returnTicket(id);
    }

    @Override
    public List<Ticket> getAllSoldTicketsFromSession(Integer id) {
        //for DB
        return null;
    }

    @Override
    public void checkTicket(int row, int column, int sessionId) throws TicketPurchaseException {

    }


}
