package com.cinema.dao.impl;

import com.cinema.dao.api.SessionDAO;
import com.cinema.model.Session;

import java.time.LocalDate;

/**
 * Created by Елена on 10.04.2016.
 */
public class SessionDAODB extends CrudDAODataBase<Session, Integer> implements SessionDAO<Session, Integer> {
    private static SessionDAODB sessionDAO;

    public synchronized static SessionDAODB getInstnce() {

        if (sessionDAO == null) {
            sessionDAO = new SessionDAODB(Session.class);
        }
        return sessionDAO;
    }

    private SessionDAODB(Class type) {
        super(type);
    }

    @Override
    public void createSession(int movieId, int hallId, LocalDate date) {
        //todo
    }
}
