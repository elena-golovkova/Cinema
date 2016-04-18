package com.cinema.controller;

import com.cinema.dto.UserDTO;
import com.cinema.service.api.UserService;
import com.cinema.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {

        request.getRequestDispatcher("pages/loginnew.jsp").forward(request, resp);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        UserDTO user = new UserDTO();



        String login = request.getParameter("login");
        if (login == null || login.trim().isEmpty()) {
            messages.put("login", "Please enter login");
        }
        String pass = request.getParameter("pass");
        if (pass == null || pass.trim().isEmpty()) {
            messages.put("pass", "Please enter pass");
        }
        if (messages.isEmpty()) {
            messages.put("success", "Form successfully submitted!");
        }

        request.getRequestDispatcher("/pages/loginnew.jsp").forward(request, response);

       /* UserService userService = UserServiceImpl.getInstance();
        user = userService.findUser(login, pass);
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            response.sendRedirect("pages/userLogged.jsp"); //logged-in page
        } else {

            request.setAttribute("noSuchUser", "Login or password incorrect" );
            request.getRequestDispatcher("pages/invalidLogin.jsp").forward(request, response);

        }*/


    }

}
