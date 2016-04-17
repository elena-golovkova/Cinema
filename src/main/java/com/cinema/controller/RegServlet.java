package com.cinema.controller;


import com.cinema.dto.UserDTO;
import com.cinema.exception.UserLoginException;
import com.cinema.model.Role;
import com.cinema.service.api.UserService;
import com.cinema.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/registration")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("pages/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO user = new UserDTO();
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String [] date = request.getParameter("date").split("-");

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(pass);
        user.setRole(Role.USER);
        if (date.length == 3){
            int year = Integer.valueOf(date[0]);
            int month = Integer.valueOf(date[1]);
            int day = Integer.valueOf(date[2]);
            user.setDate(year,month,day);

        }
        UserService userService = UserServiceImpl.getInstance();
        try {
            userService.createUser(user);
            response.sendRedirect("pages/login.jsp");
        } catch (UserLoginException e) {

            response.sendRedirect("pages/invalidLogin.jsp");
        }

    }

}
