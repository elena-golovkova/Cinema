package com.cinema.controller;

import com.cinema.dto.SessionDTO;
import com.cinema.dto.TicketDTO;
import com.cinema.exception.MovieExistException;
import com.cinema.service.api.MovieService;
import com.cinema.service.api.SessionService;
import com.cinema.service.api.TicketService;
import com.cinema.service.impl.MovieServiceImpl;
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

@WebServlet("/deleteMovie")
public class DeleteMovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/movie");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        String movieId = request.getParameter("movieId");

        MovieService movieService = MovieServiceImpl.getInstance();
        SessionService sessionService = SessionServiceImpl.getInstance();
        TicketService ticketService = TicketServiceImpl.getInstance();
        List<SessionDTO> list = new LinkedList<>();
        List<TicketDTO> ticketDTOs = new LinkedList<>();

        if (movieId == null) {
            messages.put("wrongId", "Movie's id is null");
        } else {
            try {
                Integer id = Integer.valueOf(movieId);
                movieService.checkMovieIfNotExist(id);
                list = sessionService.getALLSessionsForMovie(id);
                movieService.delete(id);
            } catch (NumberFormatException e) {
                messages.put("wrongId", "Movie's id is incorrect");
            } catch (MovieExistException e) {
                messages.put("wrongId", "There is no such movie");
            }
        }
        if (list.size() > 0) {
            for (SessionDTO sessionDTO : list) {
                ticketDTOs = ticketService.getAllSoldTicketFromSession(sessionDTO.getId());
                sessionService.delete(sessionDTO.getId());
                if (ticketDTOs.size() > 0) {
                    for (TicketDTO ticketDTO : ticketDTOs) {
                        ticketService.delete(ticketDTO.getId());
                    }
                }
            }
        }

        if (messages.size() > 0) {
            request.getRequestDispatcher("/movie").forward(request, response);
        } else {
            response.sendRedirect("/movie");
        }

    }
}
