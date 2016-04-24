package com.cinema.dao.api;

import com.cinema.exception.NoSessionException;
import com.cinema.exception.UserLoginException;
import com.cinema.model.Session;
import com.cinema.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SessionDAO<Session, Integer> extends Dao<Session, Integer> {
    public List<Session> getALLSessionWithAllData();
    public List<Session> getALLSessionsForMovie(Integer id);
    public Session getSessionWithData(Integer id) throws NoSessionException;
}
