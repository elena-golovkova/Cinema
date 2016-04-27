package com.cinema.controller;

import com.cinema.exception.MovieExistException;
import com.cinema.service.api.MovieService;
import com.cinema.service.impl.MovieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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
        if (movieId == null) {
            messages.put("wrongId", "Movie's id is null");
        } else {
            MovieService movieService = MovieServiceImpl.getInstance();
            try {
                movieService.checkMovie("id", movieId);
                movieService.delete(Integer.valueOf(movieId));
            } catch (MovieExistException e) {
                messages.put("wrongId", "There is no such movie");
            } catch (NumberFormatException e){
                messages.put("wrongId", "Movie's id is incorrect");
            }
        }
        if (messages.size() > 0){
            request.getRequestDispatcher("/pages/editMovie.jsp").forward(request, response);
        }else {
            response.sendRedirect("/movie");
        }

    }
}
