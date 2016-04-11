package com.cinema.dto;

import com.cinema.model.Hall;
import com.cinema.model.Movie;
import com.cinema.model.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class SessionDTO {
    private Integer id;
    private LocalDateTime date;
    private Hall hall;
    private Movie movie;
    private List<Ticket> tickets = new LinkedList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDate(int year, int month, int day, int hour, int minute) {
        this.date = LocalDateTime.of(year, month, day, hour, minute);
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

    public SessionDTO(Integer id, LocalDateTime date, Hall hall, Movie movie, List<Ticket> tickets) {
        setId(id);
        setDate(date);
        setHall(hall);
        setMovie(movie);
        setTickets(tickets);
    }

    public SessionDTO(Integer id, int year, int month, int day, int hour, int minute,  Hall hall, Movie movie, List<Ticket> tickets) {
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
