package com.cinema.dao.impl;

import com.cinema.dao.api.SessionDAO;
import com.cinema.dao.storage.InMemoryDB;
import com.cinema.model.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public final class SessionDAOImpl extends AbstractDAOInMemory<Session, Integer> implements SessionDAO<Session, Integer> {
    private static SessionDAOImpl sessionDAO;

    public synchronized static SessionDAOImpl getInstance() {

        if (sessionDAO == null) {
            sessionDAO = new SessionDAOImpl(Session.class);
        }
        return sessionDAO;
    }

    private SessionDAOImpl(Class type) {
        super(type);
    }


    @Override
    public List<Session> getALLSessionWithAllData() {
        List<Session> list = super.getAll();
        return list;
    }


}
