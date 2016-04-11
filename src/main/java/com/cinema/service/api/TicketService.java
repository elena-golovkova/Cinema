package com.cinema.service.api;

import com.cinema.dto.TicketDTO;
import com.cinema.model.Ticket;

import java.util.List;

public interface TicketService extends Service<TicketDTO, Integer> {

    TicketDTO purchaseTicket(int row, int column, int sessionId);
    TicketDTO returnTicket(int row, int column, int sessionId);
    public List<TicketDTO> getAllSoldTicketFromSession(Integer id);
}
