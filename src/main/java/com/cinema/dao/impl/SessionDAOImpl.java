package com.cinema.dao.impl;

import com.cinema.dao.api.SessionDAO;
import com.cinema.dao.storage.InMemoryDB;
import com.cinema.model.Session;
import java.time.LocalDate;


public final class SessionDAOImpl extends AbstractDAOInMemory<Session, Integer> implements SessionDAO<Session, Integer> {
    private static SessionDAOImpl sessionDAO;

    public synchronized static SessionDAOImpl getInstnce() {

        if (sessionDAO == null) {
            sessionDAO = new SessionDAOImpl(Session.class);
        }
        return sessionDAO;
    }

    private SessionDAOImpl(Class type) {
        super(type);
    }

    public void createSession(int movieId, int hallId, LocalDate date) {
        InMemoryDB instance = InMemoryDB.getInstance();
        instance.createSession(movieId, hallId, date);
    }

}
