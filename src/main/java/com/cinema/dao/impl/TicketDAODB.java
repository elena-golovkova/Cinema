package com.cinema.dao.impl;

import com.cinema.dao.api.TicketDAO;
import com.cinema.exception.TicketPurchaseException;
import com.cinema.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class TicketDAODB extends CrudDAODataBase<Ticket, Integer> implements TicketDAO<Ticket, Integer> {
    private static TicketDAODB ticketDAO;
    private static final String SELECT_ALL_SOLD_TICKET = "select * from ticket \n" +
            "join session on session.id = ticket.session_id where session.id = ?";
    private static final String PURCHASE = "Insert into ticket (row, place, session_id) values (?,?,?)";
    private static final String RETURN = "Delete from ticket where id = ?";
    private  static final String FIND_TICKET = "select * from ticket where row = ? and place = ? and session_id = ?";

    public synchronized static TicketDAODB getInstance() {

        if (ticketDAO == null) {
            ticketDAO = new TicketDAODB(Ticket.class);
        }
        return ticketDAO;
    }

    private TicketDAODB(Class type) {
        super(type);
    }

    @Override
    public void purchaseTicket(int row, int column, int sessionId) throws TicketPurchaseException {
        checkTicket(row, column, sessionId);
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(PURCHASE);
            preparedStatement.setInt(1, row);
            preparedStatement.setInt(2, column);
            preparedStatement.setInt(3, sessionId);
            preparedStatement.executeUpdate();
            }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void returnTicket(Integer id) {

        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(RETURN);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Ticket> getAllSoldTicketsFromSession(Integer sessionId) {
        List<Ticket> tickets = new LinkedList<>();
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_SOLD_TICKET);
            preparedStatement.setInt(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getInt(1)); //id
                ticket.setRow(resultSet.getInt(2)); //row
                ticket.setColumn(resultSet.getInt(3)); // place
                tickets.add(ticket);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;

    }

    @Override
    public void checkTicket(int row, int column, int sessionId) throws TicketPurchaseException {
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = null;
        Ticket ticket = null;

        try {
            preparedStatement = connection.prepareStatement(FIND_TICKET);
            preparedStatement.setInt(1, row);
            preparedStatement.setInt(2, column);
            preparedStatement.setInt(3, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ticket = new Ticket();
                ticket.setId(resultSet.getInt("id"));
                ticket.setRow(resultSet.getInt("row"));
                ticket.setColumn(resultSet.getInt("place"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (ticket != null) throw new TicketPurchaseException();

    }

}
