package com.cinema.controller;


import com.cinema.controller.listener.EmailValidator;
import com.cinema.dto.UserDTO;
import com.cinema.exception.UserLoginException;
import com.cinema.model.Role;
import com.cinema.service.api.UserService;
import com.cinema.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        String firstName = request.getParameter("fname");
        if (firstName == null || firstName.trim().isEmpty()) {
            messages.put("fname", "First Name can not be empty");
            request.getRequestDispatcher("/pages/reg.jsp").forward(request, response);
        }
        String lastName = request.getParameter("lname");
        if (lastName == null || lastName.trim().isEmpty()) {
            messages.put("lname", "Last Name can not be empty");
            request.getRequestDispatcher("/pages/reg.jsp").forward(request, response);
        }
        String login = request.getParameter("login");
        if (login == null || login.trim().isEmpty()) {
            messages.put("login", "Login can not be empty");
            request.getRequestDispatcher("/pages/reg.jsp").forward(request, response);
        }
        String pass = request.getParameter("pass");
        if (pass == null || pass.trim().isEmpty()) {
            messages.put("pass", "Password can not be empty");
            request.getRequestDispatcher("/pages/reg.jsp").forward(request, response);
        }
        String email = request.getParameter("email");
      /*  if (email != null || !email.trim().isEmpty()) {
           if( !EmailValidator.validate(email)) {
               messages.put("email", "Invalid email");
               request.getRequestDispatcher("/pages/reg.jsp").forward(request, response);
           }
        }*/
        String[] date = request.getParameter("date").split("-");

        UserDTO user = new UserDTO();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(pass);
        user.setRole(Role.USER);
        if (date.length == 3) {
            int year = Integer.valueOf(date[0]);
            int month = Integer.valueOf(date[1]);
            int day = Integer.valueOf(date[2]);
            user.setDate(year, month, day);

        }
        UserService userService = UserServiceImpl.getInstance();
        try {
            userService.createUser(user);
            request.setAttribute("success", "You registered successfully. Please login");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/reg.jsp");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);

        } catch (UserLoginException e) {
            messages.put("invalidLogin", "Login is already taken");
            request.getRequestDispatcher("/pages/reg.jsp").forward(request, response);
        }

    }

}
