package com.cinema.controller;

import com.cinema.dto.SessionDTO;
import com.cinema.exception.NoSessionException;
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

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getSession().setAttribute("message", "You should select movie first");
        req.getRequestDispatcher("pages/movie.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        String sessionid = request.getParameter("sessionid");
        SessionService sessionService = SessionServiceImpl.getInstance();
        try {
            SessionDTO sessionDTO = sessionService.getSessionWithData(Integer.valueOf(sessionid));
            int row = sessionDTO.getHall().getRowCount();
            int column = sessionDTO.getHall().getColumnCount();
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= column; j++) {
                    String str = request.getParameter("ticket"+i+j);
                    if (str!=null) {
                        System.out.println("ticket" + i + j);
                        System.out.println(str);
                        TicketService ticketService = TicketServiceImpl.getInstance();
                        ticketService.purchaseTicket(i,j, Integer.valueOf(sessionid));
                    }
                }
            }
        } catch (NoSessionException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("message", "You purchase your tickets successfully");
        request.getRequestDispatcher("pages/movie.jsp").forward(request, resp);

    }
}

