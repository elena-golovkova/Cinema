package com.cinema.web;


import com.cinema.Transformer;
import com.cinema.dto.*;
import com.cinema.exception.UserLoginException;
import com.cinema.model.Hall;
import com.cinema.model.Movie;
import com.cinema.model.Role;
import com.cinema.service.api.*;
import com.cinema.service.impl.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        TicketService ticketService = TicketServiceImpl.getInstance();
        for (int j = 1; j < 6; j++) {
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setRow(1);
            ticketDTO.setColumn(j);
            ticketService.create(ticketDTO);
        }

        HallService hallService = HallServiceImpl.getInstance();
        HallDTO hallDTO = new HallDTO();
        hallDTO.setName("blue");
        hallDTO.setColumnCount(10);
        hallDTO.setRowCount(10);
        hallService.create(hallDTO);

        for (int j = 2; j < 6; j++) {
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setRow(1);
            ticketDTO.setColumn(j);
            ticketService.create(ticketDTO);
        }
        ticketService.delete(1);


        Hall hall = Transformer.hallDtoToHall(hallDTO);
        Movie movie = new Movie();
        SessionDTO session = new SessionDTO();
        session.setDate(2025, 01, 01, 17, 06);
        session.setHall(hall);
        session.setMovie(movie);
        session.setId(1);
        SessionService sessionService = SessionServiceImpl.getInstance();
        //sessionService.create(session);


        ticketService.purchaseTicket(2, 4, 1);
        ticketService.returnTicket(3);
        UserService userService = UserServiceImpl.getInstance();

        UserDTO user = new UserDTO();

        user.setEmail("@.gmail.com");
        user.setPassword("15");
        user.setLogin("lena7");
        user.setRole(Role.USER);

        try {
            userService.createUser(user);
        } catch (UserLoginException e) {
            e.printStackTrace();
        }

        MovieService movieService = MovieServiceImpl.getInstance();

        for (int i = 0; i < 10; i++) {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setTitle("Title " + Thread.currentThread().getName() + " №" + i);
            movieDTO.setDescription("Description" + Thread.currentThread().getName() + " №" + i);
            movieDTO.setDuration(1000000l + i);
            movieService.create(movieDTO);
        }

      /*  System.out.println(userService.getAll());
        System.out.println(sessionService.getAll());
        System.out.println(movieService.getAll());
        System.out.println(hallService.getAll());
        System.out.println(ticketService.getAll());*/
    }

}

