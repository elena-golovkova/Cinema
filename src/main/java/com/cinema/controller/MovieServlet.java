package com.cinema.controller;


import com.cinema.dto.MovieDTO;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<MovieDTO> movies = new ArrayList<>();
        MovieService movieService = MovieServiceImpl.getInstance();
        movies = movieService.getAll();
        req.getSession().setAttribute("movies", movies);
        req.getRequestDispatcher("pages/movie.jsp").forward(req, resp);

    }

}