package com.cinema.dao.impl;

import com.cinema.dao.api.SessionDAO;
import com.cinema.dao.storage.LocalDateTimePersistenceConverter;
import com.cinema.model.Hall;
import com.cinema.model.Movie;
import com.cinema.model.Session;
import com.cinema.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class SessionDAODB extends CrudDAODataBase<Session, Integer> implements SessionDAO<Session, Integer> {
    private static SessionDAODB sessionDAO;
    LocalDateTimePersistenceConverter converter = new LocalDateTimePersistenceConverter();
    private static final String SELECT_ALL_DATA = "select * from session join movie on session.movie_id = movie.id\n" +
            "join hall on session.hall_id = hall.id";
    private static final String SELECT_SESSION_FOR_MOVIE = "select * from session join movie on session.movie_id = movie.id\n" +
            "join hall on session.hall_id = hall.id where movie_id =? order by date";


    public synchronized static SessionDAODB getInstance() {

        if (sessionDAO == null) {
            sessionDAO = new SessionDAODB(Session.class);
        }
        return sessionDAO;
    }

    private SessionDAODB(Class type) {
        super(type);
    }

    public List<Session> getALLSessionWithAllData() {
        List<Session> sessionList = new LinkedList<>();
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_DATA);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LocalDateTime date = converter.convertToEntityAttribute(resultSet.getTimestamp(2));
                Session session = new Session();
                Movie movie = new Movie();
                Hall hall = new Hall();
                session.setId(resultSet.getInt(1));
                session.setDate(date);
                movie.setId(resultSet.getInt(5));
                movie.setTitle(resultSet.getString(6));
                movie.setDescription(resultSet.getString(7));
                movie.setDuration(resultSet.getLong(8));
                hall.setId(resultSet.getInt(9));
                hall.setName(resultSet.getString(10));
                hall.setRowCount(resultSet.getInt(11));
                hall.setColumnCount(resultSet.getInt(12));
                session.setMovie(movie);
                session.setHall(hall);
                sessionList.add(session);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sessionList;
    }

    @Override
    public List<Session> getALLSessionsForMovie(Integer id) {
        List<Session> sessionList = new LinkedList<>();
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_SESSION_FOR_MOVIE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LocalDateTime date = converter.convertToEntityAttribute(resultSet.getTimestamp(2));
                Session session = new Session();
                Movie movie = new Movie();
                Hall hall = new Hall();
                session.setId(resultSet.getInt(1));
                session.setDate(date);
                movie.setId(resultSet.getInt(5));
                movie.setTitle(resultSet.getString(6));
                movie.setDescription(resultSet.getString(7));
                movie.setDuration(resultSet.getLong(8));
                hall.setId(resultSet.getInt(9));
                hall.setName(resultSet.getString(10));
                hall.setRowCount(resultSet.getInt(11));
                hall.setColumnCount(resultSet.getInt(12));
                session.setMovie(movie);
                session.setHall(hall);
                sessionList.add(session);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sessionList;
    }

}
