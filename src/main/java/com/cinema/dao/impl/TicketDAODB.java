package com.cinema.dao.impl;

import com.cinema.dao.api.TicketDAO;
import com.cinema.model.Session;
import com.cinema.model.Ticket;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class TicketDAODB extends CrudDAODataBase<Ticket, Integer> implements TicketDAO<Ticket, Integer> {
    private static TicketDAODB ticketDAO;
    private static final String SELECT_ALL_SOLD_TICKET = "select * from session \n" +
            "join ticket on session.id = ticket.session_id where session.id = ?;";

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

    @Override
    public List<Ticket> getAllSoldTicketFromSession(Integer sessionId) {
        List<Ticket> tickets = new LinkedList<>();
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_SOLD_TICKET);
            preparedStatement.setInt(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getInt("id"));
                ticket.setRow(resultSet.getInt("row"));
                ticket.setColumn(resultSet.getInt("place"));
                tickets.add(ticket);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;

    }
}
