package com.cinema.service.impl;

import com.cinema.Transformer;
import com.cinema.dao.api.TicketDAO;
import com.cinema.dao.impl.TicketDAODB;
import com.cinema.dao.impl.TicketDAOImpl;
import com.cinema.dao.storage.Configuration;
import com.cinema.dto.TicketDTO;
import com.cinema.model.Ticket;
import com.cinema.service.api.TicketService;

import java.util.LinkedList;
import java.util.List;

public final class TicketServiceImpl implements TicketService {
    private static TicketServiceImpl ticketService;
    boolean isDB = Boolean.valueOf(Configuration.getInstance().getProperty(Configuration.DB));

    public synchronized static TicketServiceImpl getInstance() {
        if (ticketService == null) {
            ticketService = new TicketServiceImpl();
        }
        return ticketService;
    }

    private TicketServiceImpl() {
    }

    @Override
    public TicketDTO purchaseTicket(int row, int column, int sessionId) {
        TicketDTO ticketDTO = null;
        if (!isDB) {
            TicketDAO ticketDAO = TicketDAOImpl.getInstance();
            Ticket ticket = (Ticket) ticketDAO.purchaseTicket(row, column, sessionId);
            ticketDTO = Transformer.ticketToTicketDTO(ticket);
        } else {
            TicketDAO ticketDAO = TicketDAODB.getInstance();
            Ticket ticket = (Ticket) ticketDAO.purchaseTicket(row, column, sessionId);
            ticketDTO = Transformer.ticketToTicketDTO(ticket);
        }
        return ticketDTO;
    }

    @Override
    public TicketDTO returnTicket(int row, int column, int sessionId) {
        TicketDTO ticketDTO = null;
        if (!isDB) {
            TicketDAO ticketDAO = TicketDAOImpl.getInstance();
            Ticket ticket = (Ticket) ticketDAO.returnTicket(row, column, sessionId);
            ticketDTO =  Transformer.ticketToTicketDTO(ticket);
        } else {
            TicketDAO ticketDAO = TicketDAODB.getInstance();
            Ticket ticket = (Ticket) ticketDAO.returnTicket(row, column, sessionId);
            ticketDTO =  Transformer.ticketToTicketDTO(ticket);
        }
        return  ticketDTO;
    }

    @Override
    public List<TicketDTO> getAllSoldTicketFromSession(Integer id) {
        List<TicketDTO> ticketDTOs = new LinkedList<>();
        if (isDB) {
            TicketDAO ticketDAO = TicketDAODB.getInstance();
            List<Ticket> tickets = ticketDAO.getAllSoldTicketFromSession(id);
            ticketDTOs = Transformer.listSeatsToListSeatsDTO(tickets);
        }
        return ticketDTOs;
    }

    @Override
    public List<TicketDTO> getAll() {
        List<TicketDTO> ticketDTOs = new LinkedList<>();
        if (!isDB) {
            TicketDAO ticketDAO = TicketDAOImpl.getInstance();
            List<Ticket> tickets = ticketDAO.getAll();
            ticketDTOs = Transformer.listSeatsToListSeatsDTO(tickets);
        } else {
            TicketDAO ticketDAO = TicketDAODB.getInstance();
            List<Ticket> tickets = ticketDAO.getAll();
            ticketDTOs = Transformer.listSeatsToListSeatsDTO(tickets);
        }
        return ticketDTOs;
    }

    @Override
    public void create(TicketDTO ticketDto) {
        if (!isDB) {
            TicketDAO ticketDAO = TicketDAOImpl.getInstance();
            Ticket ticket = Transformer.ticketDTOToTicket(ticketDto);
            ticketDAO.create(ticket);
        }else {
            TicketDAO ticketDAO = TicketDAODB.getInstance();
            Ticket ticket = Transformer.ticketDTOToTicket(ticketDto);
            ticketDAO.create(ticket);
        }
    }

    @Override
    public TicketDTO get(Integer id) {
        TicketDTO ticketDTO = null;
        if (!isDB) {
            TicketDAO ticketDAO = TicketDAOImpl.getInstance();
            Ticket ticket = (Ticket) ticketDAO.get(id);
            ticketDTO = Transformer.ticketToTicketDTO(ticket);
        }else {
            TicketDAO ticketDAO = TicketDAODB.getInstance();
            Ticket ticket = (Ticket) ticketDAO.get(id);
            ticketDTO = Transformer.ticketToTicketDTO(ticket);
        }
        return ticketDTO;
    }

    @Override
    public TicketDTO update(TicketDTO ticketDTO) {
        TicketDTO ticketDTO1 = null;
        if (!isDB) {
            TicketDAO ticketDAO = TicketDAOImpl.getInstance();
            Ticket ticket = (Ticket) ticketDAO.update(Transformer.ticketDTOToTicket(ticketDTO));
            ticketDTO1 = Transformer.ticketToTicketDTO(ticket);
        }else {
            TicketDAO ticketDAO = TicketDAODB.getInstance();
            Ticket ticket = (Ticket) ticketDAO.update(Transformer.ticketDTOToTicket(ticketDTO));
            ticketDTO1 = Transformer.ticketToTicketDTO(ticket);
        }
        return ticketDTO1;
    }

    @Override
    public void delete(Integer id) {
        if (!isDB) {
            TicketDAO ticketDAO = TicketDAOImpl.getInstance();
            ticketDAO.delete(id);
        }else {
            TicketDAO ticketDAO = TicketDAODB.getInstance();
            ticketDAO.delete(id);
        }

    }
}
