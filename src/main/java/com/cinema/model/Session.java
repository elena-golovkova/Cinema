package com.cinema.model;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Session {
    private Integer id;
    private Date date;
    private Hall hall;
    private Movie movie;
    private List<Ticket> tickets = new LinkedList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute);
        this.date = cal.getTime();
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

    public void setTickets(List<Ticket> tickets) {

        this.tickets = tickets;
    }

    public Session(Integer id, Date date, Hall hall, Movie movie, List<Ticket> tickets) {
        setId(id);
        setDate(date);
        setHall(hall);
        setMovie(movie);
        setTickets(tickets);
    }

    public Session(Integer id, int year, int month, int day, int hour, int minute, Hall hall, Movie movie, List<Ticket> tickets) {
        setId(id);
        setDate(year, month, day, hour, minute);
        setHall(hall);
        setMovie(movie);
        setTickets(tickets);
    }

    public Session() {
    }

}
