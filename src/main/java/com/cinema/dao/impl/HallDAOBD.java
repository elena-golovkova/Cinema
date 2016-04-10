package com.cinema.dao.impl;


import com.cinema.dao.api.HallDAO;
import com.cinema.model.Hall;

public class HallDAOBD extends CrudDAODataBase<Hall, Integer> implements HallDAO<Hall, Integer> {
    private static HallDAOBD hallDAO;

    public synchronized static HallDAOBD getInstance() {
        if (hallDAO == null) {
            hallDAO = new HallDAOBD(Hall.class);
        }
        return hallDAO;
    }

    private HallDAOBD(Class type) {
        super(type);
    }
}
