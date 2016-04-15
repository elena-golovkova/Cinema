package com.cinema.controller;

import com.cinema.dto.UserDTO;
import com.cinema.service.api.UserService;
import com.cinema.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {

        request.getRequestDispatcher("pages/login.jsp").forward(request, resp);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO user = new UserDTO();
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        UserService userService = UserServiceImpl.getInstance();
        user = userService.findUser(login, pass);
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser", user);
            response.sendRedirect("pages/userLogged.jsp"); //logged-in page
        } else response.sendRedirect("pages/invalidLogin.jsp"); //error page

    }
}
