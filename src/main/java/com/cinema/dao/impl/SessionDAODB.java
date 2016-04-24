package com.cinema.dao.impl;

import com.cinema.dao.api.SessionDAO;
import com.cinema.exception.NoSessionException;
import com.cinema.model.Hall;
import com.cinema.model.Movie;
import com.cinema.model.Session;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SessionDAODB extends CrudDAODataBase<Session, Integer> implements SessionDAO<Session, Integer> {
    private static SessionDAODB sessionDAO;

    private static final String SELECT_ALL_DATA = "select * from session join movie on session.movie_id = movie.id\n" +
            "join hall on session.hall_id = hall.id";
    private static final String SELECT_SESSION_FOR_MOVIE = "select * from session join movie on session.movie_id = movie.id\n" +
            "join hall on session.hall_id = hall.id where movie_id =? order by date";
    private static final String SELECT_SESSION = "select * from session join movie on session.movie_id = movie.id\n" +
            "join hall on session.hall_id = hall.id where session.id =?";


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

                Session session = new Session();
                Movie movie = new Movie();
                Hall hall = new Hall();
                session.setId(resultSet.getInt(1));
                session.setDate(resultSet.getTimestamp(2));
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

                Session session = new Session();
                Movie movie = new Movie();
                Hall hall = new Hall();
                session.setId(resultSet.getInt(1));
                session.setDate(resultSet.getTimestamp(2));
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
    public Session getSessionWithData(Integer id) throws NoSessionException {
        Session session = new Session();
        List<Session> sessionList = new LinkedList<>();
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_SESSION);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Movie movie = new Movie();
                Hall hall = new Hall();
                session.setId(resultSet.getInt(1));
                session.setDate(resultSet.getTimestamp(2));
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

        if (sessionList.size()>0) {
            session = sessionList.get(0);
        }
        else {
            throw new NoSessionException();        }
        return session;
    }

}
