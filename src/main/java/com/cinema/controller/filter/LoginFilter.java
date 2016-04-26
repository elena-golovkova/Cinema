package com.cinema.controller.filter;

import com.cinema.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginFilter implements Filter {
    private String LOGIN_ACTION_URI;
    private final String REGISTRATION_ACTION_URI = "/registration";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGIN_ACTION_URI = filterConfig.getInitParameter("loginActionURI");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null && !LOGIN_ACTION_URI.equals(request.getRequestURI()) && !REGISTRATION_ACTION_URI.equals(request.getRequestURI())) {
            RequestDispatcher rd = request.getRequestDispatcher("/login");
            rd.forward(request, response);
            return;
        }

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
