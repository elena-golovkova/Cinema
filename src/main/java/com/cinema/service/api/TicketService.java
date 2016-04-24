package com.cinema.service.api;

import com.cinema.dto.TicketDTO;
import com.cinema.exception.TicketPurchaseException;
import com.cinema.model.Ticket;

import java.util.List;

public interface TicketService extends Service<TicketDTO, Integer> {

    void purchaseTicket(int row, int column, int sessionId) throws TicketPurchaseException;
    void returnTicket(int id);
    public List<TicketDTO> getAllSoldTicketFromSession(Integer id);
    void checkTicket(int row, int column, int sessionId) throws TicketPurchaseException;
}
