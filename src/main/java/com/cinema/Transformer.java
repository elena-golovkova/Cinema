package com.cinema;

import com.cinema.dto.*;
import com.cinema.model.*;

import java.util.LinkedList;
import java.util.List;

public class Transformer {

    public static List<MovieDTO> listMovieToListMovieDTO(List<Movie> movies) {
        List<MovieDTO> movieDTOs = new LinkedList<MovieDTO>();
        synchronized (movies) {
            for (Movie movie : movies) {
                MovieDTO movieDTO = movieToMovieDto(movie);
                movieDTOs.add(movieDTO);
            }
        }
        return movieDTOs;
    }

    public static MovieDTO movieToMovieDto(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setDuration(movie.getDuration());

        return movieDTO;
    }

    public static Movie movieDTOTMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setDescription(movieDTO.getDescription());
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setDuration(movieDTO.getDuration());
        return movie;

    }

    public static User userDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDate(userDTO.getDate());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        return user;
    }

    public static List<UserDTO> listUserToListUserDTO(List<User> users) {
        List<UserDTO> userDTOs = new LinkedList<UserDTO>();
        synchronized (users) {
            for (User user : users) {
                UserDTO userDTO = userToUserDto(user);
                userDTOs.add(userDTO);
            }
        }
        return userDTOs;
    }

    public static UserDTO userToUserDto(User user) {
        UserDTO userDTO = null;
        if (user != null) {
            userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setLogin(user.getLogin());
            userDTO.setPassword(user.getPassword());
            userDTO.setEmail(user.getEmail());
            userDTO.setRole(user.getRole());
            userDTO.setDate(user.getDate());
        }
        return userDTO;
    }

    public static TicketDTO ticketToTicketDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setRow(ticket.getRow());
        ticketDTO.setColumn(ticket.getColumn());

        return ticketDTO;
    }

    public static List<TicketDTO> listSeatsToListSeatsDTO(List<Ticket> tickets) {
        List<TicketDTO> seatsDTO = new LinkedList<>();
        synchronized (tickets) {
            for (Ticket ticket : tickets) {
                TicketDTO ticketDTO = ticketToTicketDTO(ticket);
                seatsDTO.add(ticketDTO);
            }
        }
        return seatsDTO;
    }

    public static List<Ticket> listSeatDtoToListSeat(List<TicketDTO> seatsDTO) {
        List<Ticket> tickets = new LinkedList<>();
        synchronized (seatsDTO) {
            for (TicketDTO ticketDTO : seatsDTO) {
                Ticket ticket = Transformer.ticketDTOToTicket(ticketDTO);
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    public static Ticket ticketDTOToTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setRow(ticketDTO.getRow());
        ticket.setColumn(ticketDTO.getColumn());
        return ticket;
    }

    public static List<Ticket> listTicketDTOToListTicket(List<TicketDTO> ticketDTOs) {
        List<Ticket> tickets = new LinkedList<>();
        synchronized (ticketDTOs) {
            for (TicketDTO ticketDTO : ticketDTOs) {
                tickets.add(Transformer.ticketDTOToTicket(ticketDTO));
            }
        }
        return tickets;
    }

    public static HallDTO hallToHallDto(Hall hall) {
        HallDTO hallDTO = new HallDTO();
        hallDTO.setId(hall.getId());
        hallDTO.setName(hall.getName());
        hallDTO.setColumnCount(hall.getColumnCount());
        hallDTO.setRowCount(hall.getRowCount());
        return hallDTO;
    }

    public static List<HallDTO> listHallsToListHallsDto(List<Hall> halls) {
        List<HallDTO> hallsDTO = new LinkedList<>();
        synchronized (halls) {
            for (Hall hall : halls) {
                HallDTO hallDTO = Transformer.hallToHallDto(hall);
                hallsDTO.add(hallDTO);
            }
        }
        return hallsDTO;
    }

    public static Hall hallDtoToHall(HallDTO hallDTO) {
        Hall hall = new Hall();
        hall.setId(hallDTO.getId());
        hall.setName(hallDTO.getName());
        hall.setColumnCount(hallDTO.getColumnCount());
        hall.setRowCount(hallDTO.getRowCount());
        return hall;
    }

    public static SessionDTO sessionToSessionDTO(Session session) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(session.getId());
        sessionDTO.setMovie(session.getMovie());
        sessionDTO.setDate(session.getDate());
        sessionDTO.setHall(session.getHall());
        sessionDTO.setTickets(session.getTickets());
        return sessionDTO;
    }

    public static Session sessionDTOToSession(SessionDTO sessionDTO) {
        Session session = new Session();
        session.setId(sessionDTO.getId());
        session.setMovie(sessionDTO.getMovie());
        session.setDate(sessionDTO.getDate());
        session.setHall(sessionDTO.getHall());
        session.setTickets(sessionDTO.getTickets());
        return session;
    }

    public static List<SessionDTO> listSessionToListSessionDTO(List<Session> sessions) {
        if (sessions == null) throw new RuntimeException("Session list is empty");
        List<SessionDTO> sessionDTOs = new LinkedList<>();
        for (Session session : sessions) {
            sessionDTOs.add(Transformer.sessionToSessionDTO(session));
        }
        return sessionDTOs;
    }
}
