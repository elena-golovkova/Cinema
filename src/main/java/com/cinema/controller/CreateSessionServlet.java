package com.cinema.controller;


import com.cinema.Transformer;
import com.cinema.dto.HallDTO;
import com.cinema.dto.MovieDTO;
import com.cinema.dto.SessionDTO;
import com.cinema.dto.UserDTO;
import com.cinema.exception.MovieExistException;
import com.cinema.service.api.HallService;
import com.cinema.service.api.MovieService;
import com.cinema.service.api.SessionService;
import com.cinema.service.impl.HallServiceImpl;
import com.cinema.service.impl.MovieServiceImpl;
import com.cinema.service.impl.SessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/createSession")
public class CreateSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDTO user = null;
        user = (UserDTO) request.getSession().getAttribute("user");

        if (user != null && user.getRole().toString().equals("ADMIN")) {
            request.getRequestDispatcher("/pages/editMovie.jsp").forward(request, response);
        } else {
            response.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);

        String date = request.getParameter("date");
        if (date == null || date.trim().isEmpty()) {
            messages.put("date", "Date can not be empty");
            request.getRequestDispatcher("/pages/editSession.jsp").forward(request, response);
        }
        String time = request.getParameter("time");
        if (time == null || time.trim().isEmpty()) {
            messages.put("time", "Time can not be empty");
            request.getRequestDispatcher("/pages/editSession.jsp").forward(request, response);
        }
        String hallId = request.getParameter("hallId");
        if (hallId == null || hallId.trim().isEmpty()) {
            messages.put("hallId", "Hall can not be empty");
            request.getRequestDispatcher("/pages/editSession.jsp").forward(request, response);
        }
        String movieId = request.getParameter("movieId");
        if (movieId == null || movieId.trim().isEmpty()) {
            messages.put("movieId", "Movie can not be empty");
            request.getRequestDispatcher("/pages/editSession.jsp").forward(request, response);
        }
        if (messages.size() != 0) {
            request.getRequestDispatcher("/pages/editSession.jsp").forward(request, response);
        } else {
            SessionService sessionService = SessionServiceImpl.getInstance();
            MovieService movieService = MovieServiceImpl.getInstance();
            HallService hallService = HallServiceImpl.getInstance();
            try {
                String[] dateArr = date.split("-");
                String[] timeArr = time.split(":");

                int year = Integer.valueOf(dateArr[0]);
                int month = Integer.valueOf(dateArr[1]);
                int day = Integer.valueOf(dateArr[2]);
                int hour = Integer.valueOf(timeArr[0]);
                int min = Integer.valueOf(timeArr[1]);

                movieService.checkMovieIfNotExist(Integer.valueOf(movieId));
                MovieDTO movieDTO = movieService.get(Integer.valueOf(movieId));
                HallDTO hallDTO = hallService.get(Integer.valueOf(hallId));
                SessionDTO session = new SessionDTO();
                session.setHall(Transformer.hallDtoToHall(hallDTO));
                session.setMovie(Transformer.movieDTOTMovie(movieDTO));
                session.setDate(year, month, day, hour, min);
                sessionService.create(session);
            } catch (MovieExistException e) {
                messages.put("movieId", "Wrong movie id");

            } catch (NumberFormatException e) {
                messages.put("date", "Wrong date format");
            }catch (ArrayIndexOutOfBoundsException e){
                messages.put("date", "Wrong date");
            }

        }
        if (messages.size() != 0) {
            request.getRequestDispatcher("/pages/editSession.jsp").forward(request, response);
        } else {
            messages.put("createSession", "Session was created successfully");
            request.getRequestDispatcher("/movie").forward(request, response);
        }
    }
}
