package com.cinema.dto;

import com.cinema.model.Hall;
import com.cinema.model.Movie;
import com.cinema.model.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class SessionDTO {
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

    public SessionDTO(Integer id, Date date, Hall hall, Movie movie, List<Ticket> tickets) {
        setId(id);
        setDate(date);
        setHall(hall);
        setMovie(movie);
        setTickets(tickets);
    }

    public SessionDTO(Integer id, int year, int month, int day, int hour, int minute, Hall hall, Movie movie, List<Ticket> tickets) {
        setId(id);
        setDate(year, month, day, hour, minute);
        setHall(hall);
        setMovie(movie);
        setTickets(tickets);
    }

    public SessionDTO() {
    }

    @Override
    public String toString() {
        return "SessionDTO{" +
                "id=" + id +
                ", date=" + date +
                ", hall=" + hall +
                ", movie=" + movie +
                ", tickets=" + tickets +
                '}';
    }
}
