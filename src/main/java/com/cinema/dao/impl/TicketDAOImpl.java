package com.cinema.dao.impl;

import com.cinema.dao.api.TicketDAO;
import com.cinema.dao.storage.InMemoryDB;
import com.cinema.model.Ticket;

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
    public Ticket purchaseTicket(int row, int column, int sessionId) {
        InMemoryDB instance = InMemoryDB.getInstance();
        return instance.purchaseTicket(row, column, sessionId);
    }

    @Override
    public Ticket returnTicket(int row, int column, int sessionId) {
        InMemoryDB instance = InMemoryDB.getInstance();
        return instance.returnTicket(row, column, sessionId);
    }


}
