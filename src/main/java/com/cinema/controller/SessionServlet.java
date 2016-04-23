package com.cinema.controller;


import com.cinema.dto.MovieDTO;
import com.cinema.dto.SessionDTO;
import com.cinema.service.api.MovieService;
import com.cinema.service.api.SessionService;
import com.cinema.service.impl.MovieServiceImpl;
import com.cinema.service.impl.SessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/movie-sessions")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        List<SessionDTO> sessions = new ArrayList<>();
        SessionService sessionService = SessionServiceImpl.getInstance();
        sessions = sessionService.getALLSessionsForMovie(Integer.valueOf(id));
        request.getSession().setAttribute("sessions", sessions);
        request.getRequestDispatcher("pages/moviesession.jsp").forward(request, resp);

    }

}