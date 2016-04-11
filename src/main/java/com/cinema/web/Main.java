package com.cinema.web;


import com.cinema.Transformer;
import com.cinema.dto.*;
import com.cinema.model.*;
import com.cinema.service.api.*;
import com.cinema.service.impl.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

      /*  Thread thread1 = new MovieThread();
        Thread thread2 = new MovieThread();
        Thread thread3 = new MovieThread();
        Thread thread4 = new MovieThread();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        TicketService ticketService = TicketServiceImpl.getInstance();
        for (int j = 1; j < 6; j++) {
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setRow(1);
            ticketDTO.setColumn(j);
            ticketService.create(ticketDTO);
        }

        System.out.println(ticketService.getAll());
        HallService hallService = HallServiceImpl.getInstance();
        HallDTO hallDTO = new HallDTO();
        hallDTO.setName("blue");
        hallDTO.setColumnCount(10);
        hallDTO.setRowCount(10);
        hallService.create(hallDTO);
        System.out.println(hallService.getAll());
        System.out.println(hallService.get(2));
        hallService.delete(1);

         TicketService ticketService = TicketServiceImpl.getInstance();
        for (int j = 2; j < 6; j++) {
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setRow(1);
            ticketDTO.setColumn(j);
            //ticketService.create(ticketDTO);
        }
        ticketService.delete(1);
        System.out.println(ticketService.getAll());
        HallService hallService = HallServiceImpl.getInstance();
        HallDTO hallDTO = new HallDTO();
        hallDTO.setName("blue");
        hallDTO.setColumnCount(10);
        hallDTO.setRowCount(10);
        hallService.create(hallDTO);

        Hall hall = Transformer.hallDtoToHall(hallDTO);
        Movie movie = new Movie();
        SessionDTO session = new SessionDTO();
        session.setDate(2025, 01, 01, 17, 06);
        session.setHall(hall);
        session.setMovie(movie);
        session.setId(1);

        SessionService sessionService = SessionServiceImpl.getInstance();
        //sessionService.create(session);
        System.out.println(sessionService.getALLSessionWithAllData());*/
        TicketService ticketService = TicketServiceImpl.getInstance();
        System.out.println(ticketService.getAllSoldTicketFromSession(1));


    }


    public static LocalDate fromDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                .toLocalDate();
    }

}

class MovieThread extends Thread {
    public void run() {
        MovieService movieService = MovieServiceImpl.getInstance();

        for (int i = 0; i < 10; i++) {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setTitle("Title " + Thread.currentThread().getName() + " №" + i);
            movieDTO.setDescription("Description" + Thread.currentThread().getName() + " №" + i);
            movieDTO.setDuration(1000000l + i);
            movieService.create(movieDTO);

        }

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1);
        movieDTO.setTitle("~~~~~Changed Title" + Thread.currentThread().getName());
        movieDTO.setDescription("~~~~~Changed Description" + Thread.currentThread().getName());
        movieDTO.setDuration(1000000l);

    }
}

