package com.cinema.controller;

import com.cinema.dto.MovieDTO;
import com.cinema.dto.UserDTO;
import com.cinema.exception.MovieExistException;
import com.cinema.service.api.MovieService;
import com.cinema.service.api.UserService;
import com.cinema.service.impl.MovieServiceImpl;
import com.cinema.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/createMovie")
public class CreateMovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");

        if (user.getRole().toString().equals("ADMIN")) {
            request.getRequestDispatcher("/pages/createMovie.jsp").forward(request, response);
        } else {
            response.sendError(404);
            //request.getRequestDispatcher("pages/movie.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);

        String title = request.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("title", "Title can not be empty");
            request.getRequestDispatcher("/pages/createMovie.jsp").forward(request, response);
        }
        String duration = request.getParameter("duration");
        if (duration == null || duration.trim().isEmpty()) {
            messages.put("duration", "Duration can not be empty");
            request.getRequestDispatcher("/pages/createMovie.jsp").forward(request, response);
        }
        String description = request.getParameter("description");
        if (description == null || description.trim().isEmpty()) {
            messages.put("description", "Description can not be empty");
            request.getRequestDispatcher("/pages/createMovie.jsp").forward(request, response);
        }
        try {
            Long durationMovie = Long.valueOf(duration);
        } catch (NumberFormatException e) {
            messages.put("duration", "Duration - incorrect format");
        }
        MovieService movieService = MovieServiceImpl.getInstance();
        try {
            movieService.checkMovie(title);
        } catch (MovieExistException e) {
            messages.put("movieExist", "Such film is already created");
        }
        if (messages.size() > 0) {
            request.getRequestDispatcher("/pages/createMovie.jsp").forward(request, response);
        } else {
            MovieDTO movie = new MovieDTO();
            movie.setTitle(title);
            movie.setDuration(Long.valueOf(duration));
            movie.setDescription(description);
            movieService.create(movie);
            request.removeAttribute("title");
            request.removeAttribute("duration");
            request.removeAttribute("description");
            response.sendRedirect("/movie");

        }

    }

}


