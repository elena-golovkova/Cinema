package com.cinema.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Session {
    private Integer id;
    private LocalDate date;
    private Hall hall;
    private Movie movie;
    private List<Ticket> tickets = new LinkedList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDate(int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets){

        this.tickets = tickets;
    }

    public Session(Integer id, LocalDate date, Hall hall, Movie movie, List<Ticket> tickets) {
        setId(id);
        setDate(date);
        setHall(hall);
        setMovie(movie);
        setTickets(tickets);
    }

    public Session(Integer id, int year, int month, int day, Hall hall, Movie movie, List<Ticket> tickets) {
        setId(id);
        setDate(year, month, day);
        setHall(hall);
        setMovie(movie);
        setTickets(tickets);
    }

    public Session() {
    }

    public void addTicketToList(Ticket ticket){
        tickets.add(ticket);
    }

    public void removeTicketFromList(Ticket ticket){
        try {
            tickets.remove(ticket);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
