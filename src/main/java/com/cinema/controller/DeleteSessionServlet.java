package com.cinema.controller;

import com.cinema.dto.TicketDTO;
import com.cinema.dto.UserDTO;
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
import java.util.LinkedList;
import java.util.List;

@WebServlet("/deleteSession")
public class DeleteSessionServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDTO user = null;
        user = (UserDTO) request.getSession().getAttribute("user");

        if (user != null && user.getRole().toString().equals("ADMIN")) {
            request.getRequestDispatcher("/movie").forward(request, response);
        } else {
            response.sendError(404);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String id = request.getParameter("sessionid");
        SessionService sessionService = SessionServiceImpl.getInstance();
        TicketService ticketService = TicketServiceImpl.getInstance();
        List<TicketDTO> list = new LinkedList<>();
        list = ticketService.getAllSoldTicketFromSession(Integer.valueOf(id));
        sessionService.delete(Integer.valueOf(id));
        if(list.size()>0){
            for (TicketDTO ticketDTO : list) {
                ticketService.delete(ticketDTO.getId());
            }
        }

        request.setAttribute("sessionDelete", "Session was deleted successfully");
        request.getRequestDispatcher("/movie").forward(request, resp);

    }
}
