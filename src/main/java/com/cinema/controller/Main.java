package com.cinema.controller;


import com.cinema.Transformer;
import com.cinema.dao.storage.Configuration;
import com.cinema.dto.*;
import com.cinema.exception.UserLoginException;
import com.cinema.model.Hall;
import com.cinema.model.Movie;
import com.cinema.model.Role;
import com.cinema.service.api.*;
import com.cinema.service.impl.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
       /* TicketService ticketService = TicketServiceImpl.getInstance();
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
         MovieService movieService = MovieServiceImpl.getInstance();

        for (int i = 0; i < 10; i++) {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setTitle("Title " + Thread.currentThread().getName() + " №" + i);
            movieDTO.setDescription("Description" + Thread.currentThread().getName() + " №" + i);
            movieDTO.setDuration(1000000l + i);
            movieService.create(movieDTO);
        }

       System.out.println(userService.getAll());
        System.out.println(sessionService.getAll());
        System.out.println(movieService.getAll());
        System.out.println(hallService.getAll());
        System.out.println(ticketService.getAll());



       UserService userService = UserServiceImpl.getInstance();

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Lena16");
        userDTO.setLastName("Golovkova");
        userDTO.setEmail("golovkova@gmail.com");
        userDTO.setPassword("18");
        userDTO.setLogin("18");
        userDTO.setRole(Role.USER);

        System.out.println(userService.findUser("17", "17"));

        MovieService movieService = MovieServiceImpl.getInstance();
        for (int i = 0; i < 10; i++) {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setTitle("Title " + Thread.currentThread().getName() + " №" + i);
            movieDTO.setDescription("Description" + Thread.currentThread().getName() + " №" + i);
            movieDTO.setDuration(1000000l + i);
            movieService.create(movieDTO);
        }*/

        String s = "chuprina.engmail.com";
        boolean b = EmailValidator.validate(s);
        System.out.println(b);


    }


}

