package com.cinema.service.impl;

import com.cinema.Transformer;
import com.cinema.dao.api.SessionDAO;
import com.cinema.dao.impl.SessionDAODB;
import com.cinema.dao.impl.SessionDAOImpl;
import com.cinema.dao.storage.Configuration;
import com.cinema.dto.SessionDTO;
import com.cinema.model.Session;
import com.cinema.service.api.SessionService;

import java.util.List;


public final class SessionServiceImpl implements SessionService {
    private static SessionServiceImpl sessionService;
    boolean isDB = Boolean.valueOf(Configuration.getInstance().getProperty(Configuration.DB));

    public synchronized static SessionServiceImpl getInstance() {
        if (sessionService == null) {
            sessionService = new SessionServiceImpl();
        }
        return sessionService;
    }

    private SessionServiceImpl() {
    }

    @Override
    public List<SessionDTO> getAll() {
        List<SessionDTO> sessionDTOs = null;
        if (!isDB) {
            SessionDAO sessionDAO = SessionDAOImpl.getInstance();
            List<Session> sessions = sessionDAO.getAll();
            sessionDTOs = Transformer.listSessionToListSessionDTO(sessions);
        } else {
            SessionDAO sessionDAO = SessionDAODB.getInstance();
            List<Session> sessions = sessionDAO.getAll();
            sessionDTOs = Transformer.listSessionToListSessionDTO(sessions);
        }
        return sessionDTOs;
    }

    @Override
    public void create(SessionDTO sessionDTO) {
        if (!isDB) {
            SessionDAO sessionDAO = SessionDAOImpl.getInstance();
            Session session = Transformer.sessionDTOToSession(sessionDTO);
            sessionDAO.create(session);
        } else {
            SessionDAO sessionDAO = SessionDAODB.getInstance();
            Session session = Transformer.sessionDTOToSession(sessionDTO);
            sessionDAO.create(session);
        }
    }

    @Override
    public SessionDTO get(Integer id) {
        SessionDTO sessionDTO = null;
        if (!isDB) {
            SessionDAO sessionDAO = SessionDAOImpl.getInstance();
            Session session = (Session) sessionDAO.get(id);
            sessionDTO = Transformer.sessionToSessionDTO(session);
        } else {
            SessionDAO sessionDAO = SessionDAODB.getInstance();
            Session session = (Session) sessionDAO.get(id);
            sessionDTO = Transformer.sessionToSessionDTO(session);
        }
        return sessionDTO;
    }

    @Override
    public SessionDTO update(SessionDTO sessionDTO) {
        SessionDTO sessionDTONew = null;
        if (!isDB) {
            SessionDAO sessionDAO = SessionDAOImpl.getInstance();
            Session session = (Session) sessionDAO.update(Transformer.sessionDTOToSession(sessionDTO));
            sessionDTONew = Transformer.sessionToSessionDTO(session);

        } else {
            SessionDAO sessionDAO = SessionDAODB.getInstance();
            Session session = (Session) sessionDAO.update(Transformer.sessionDTOToSession(sessionDTO));
            sessionDTONew = Transformer.sessionToSessionDTO(session);

        }
        return sessionDTONew;
    }

    @Override
    public void delete(Integer id) {
        if (!isDB) {
            SessionDAO sessionDAO = SessionDAOImpl.getInstance();
            sessionDAO.delete(id);
        } else {
            SessionDAO sessionDAO = SessionDAODB.getInstance();
            sessionDAO.delete(id);
        }
    }

    public List<SessionDTO> getALLSessionWithAllData() {
        List<SessionDTO> sessionDTOs = null;
        if (isDB) {
            SessionDAO sessionDAO = SessionDAODB.getInstance();
            List<Session> sessions = sessionDAO.getALLSessionWithAllData();
            sessionDTOs = Transformer.listSessionToListSessionDTO(sessions);
        }else{
            SessionDAO sessionDAO = SessionDAOImpl.getInstance();
            List<Session> sessions = sessionDAO.getALLSessionWithAllData();
            sessionDTOs = Transformer.listSessionToListSessionDTO(sessions);
        }
        return sessionDTOs;
    }
}
