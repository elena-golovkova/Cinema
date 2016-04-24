package com.cinema.controller;

import com.cinema.dto.SessionDTO;
import com.cinema.dto.TicketDTO;
import com.cinema.exception.NoSessionException;
import com.cinema.model.Ticket;
import com.cinema.service.api.SessionService;
import com.cinema.service.api.TicketService;
import com.cinema.service.impl.SessionServiceImpl;
import com.cinema.service.impl.TicketServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {

        request.getRequestDispatcher("pages/movie.jsp").forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = request.getParameter("sessionid");
        List<TicketDTO> listTickets = new LinkedList<>();
        if (id != null) {
            SessionService sessionService = SessionServiceImpl.getInstance();
            TicketService ticketService = TicketServiceImpl.getInstance();
            SessionDTO sessionDTO = null;
            try {
                sessionDTO = sessionService.getSessionWithData(Integer.valueOf(id));
                listTickets = ticketService.getAllSoldTicketFromSession(Integer.valueOf(id));
            } catch (NoSessionException e) {
                request.getRequestDispatcher("pages/tickets.jsp").forward(request, resp);
            }
            request.getSession().setAttribute("session", sessionDTO);
            request.getSession().setAttribute("purchasedTickets", listTickets);

        }
        request.getRequestDispatcher("pages/tickets.jsp").forward(request, resp);

    }
}
