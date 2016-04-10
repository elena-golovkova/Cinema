package com.cinema.dao.impl;

import com.cinema.dao.api.TicketDAO;
import com.cinema.model.Ticket;

import javax.persistence.criteria.CriteriaBuilder;


public class TicketDAODB extends CrudDAODataBase<Ticket,Integer> implements TicketDAO<Ticket, Integer> {
    private static TicketDAODB ticketDAO;

    public synchronized static TicketDAODB getInstance() {

        if (ticketDAO == null) {
            ticketDAO = new TicketDAODB(Ticket.class);

        }
        return ticketDAO;
    }
    private TicketDAODB(Class type) {
        super(type);
    }
//// TODO: 10.04.2016
    @Override
    public Ticket purchaseTicket(int row, int column, int sessionId) {
        return null;
    }
// // TODO: 10.04.2016
    @Override
    public Ticket returnTicket(int row, int column, int sessionId) {
        return null;
    }
}
