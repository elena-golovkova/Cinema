package com.cinema.controller;


import com.cinema.dto.MovieDTO;
import com.cinema.dto.UserDTO;
import com.cinema.service.api.MovieService;
import com.cinema.service.impl.MovieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/movie")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<MovieDTO> movies = new ArrayList<>();
        MovieService movieService = MovieServiceImpl.getInstance();
        movies = movieService.getAll();
        request.getSession().setAttribute("movies", movies);
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");

        if (user.getRole().toString().equals("ADMIN")) {
            request.getRequestDispatcher("/pages/editMovie.jsp").forward(request, response);
        } else request.getRequestDispatcher("pages/movie.jsp").forward(request, response);
    }

}



