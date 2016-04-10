package com.cinema.dao.impl;

import com.cinema.dao.api.HallDAO;
import com.cinema.model.Hall;

public class HallDAOImpl extends AbstractDAOInMemory<Hall, Integer> implements HallDAO<Hall, Integer> {
    private static HallDAOImpl hallDAO;

    public synchronized static HallDAOImpl getInstance() {
        if (hallDAO == null) {
            hallDAO = new HallDAOImpl(Hall.class);
        }
        return hallDAO;
    }

    private HallDAOImpl(Class type) {
        super(type);
    }

}
