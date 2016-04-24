package com.cinema.controller;

import com.cinema.dto.SessionDTO;
import com.cinema.dto.TicketDTO;
import com.cinema.exception.NoSessionException;
import com.cinema.service.api.SessionService;
import com.cinema.service.impl.SessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
        if (id != null) {
            SessionService sessionService = SessionServiceImpl.getInstance();
            SessionDTO sessionDTO = null;
            try {
                sessionDTO = sessionService.getSessionWithData(Integer.valueOf(id));
            } catch (NoSessionException e) {

                request.getRequestDispatcher("pages/tickets.jsp").forward(request, resp);
            }
            request.getSession().setAttribute("session", sessionDTO);

        }
        request.getRequestDispatcher("pages/tickets.jsp").forward(request, resp);

    }
}
