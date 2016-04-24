package com.cinema.controller;

import com.cinema.dto.SessionDTO;
import com.cinema.exception.NoSessionException;
import com.cinema.exception.TicketPurchaseException;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        messages.put("wrongpage", "Select movie first");
        request.getRequestDispatcher("/movie").forward(request, resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sessionid = request.getParameter("sessionid");
        Integer id = Integer.valueOf(sessionid);
        List<List<Integer>> ticketsList = new LinkedList<>();
        TicketService ticketService = TicketServiceImpl.getInstance();
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);

        SessionService sessionService = SessionServiceImpl.getInstance();
        try {
            SessionDTO sessionDTO = sessionService.getSessionWithData(id);
            int row = sessionDTO.getHall().getRowCount();
            int column = sessionDTO.getHall().getColumnCount();
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= column; j++) {
                    String str = request.getParameter("ticket" + i + j);
                    if (str != null) {
                        ticketService.checkTicket(i, j, id);
                        List<Integer> ticket = new LinkedList<>();
                        ticket.add(0, i);
                        ticket.add(1, j);
                        ticket.add(2, id);
                        ticketsList.add(ticket);
                    }
                }
            }
        } catch (NoSessionException e) {
            e.printStackTrace();
        } catch (TicketPurchaseException e) {
           messages.put("errorticket", "The ticket that you have chosen has already purchased. Please choose another one");
        }
        if (ticketsList.size()==0){
            messages.put("errorticket", "Please select ticket");
        }
        if (messages.size() != 0) {
            request.getRequestDispatcher("/pages/tickets.jsp").forward(request, response);
        } else {
            if (ticketsList != null) {
                for (List<Integer> integers : ticketsList) {
                    try {
                        ticketService.purchaseTicket(integers.get(0), integers.get(1), integers.get(2));
                    } catch (TicketPurchaseException e) {
                        messages.put("errorticket", "The ticket that you have chosen has already purchased. Please choose another one");
                        request.getRequestDispatcher("/pages/tickets.jsp").forward(request, response);
                    }
                }
                messages.put("success", "Thank you! Tickets were sent to your email.");

            }
            request.getRequestDispatcher("pages/movie.jsp").forward(request, response);
        }
    }
}

