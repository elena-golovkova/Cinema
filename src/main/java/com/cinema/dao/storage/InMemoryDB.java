package com.cinema.dao.storage;

import com.cinema.exception.UserException;
import com.cinema.exception.UserLoginException;
import com.cinema.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class InMemoryDB<T, K> {
    private static InMemoryDB inMemoryDB;
    private List<Movie> movies = new LinkedList<>();
    private List<User> users = new LinkedList<>();
    private List<Ticket> tickets = new LinkedList<>();
    private List<Hall> halls = new LinkedList<>();
    private List<Session> sessions = new LinkedList<>();

    private volatile int movieIdsCounter = 0;
    private volatile int userIdsCounter = 0;
    private volatile int ticketIdsCounter = 0;
    private volatile int hallIdsCounter = 0;
    private volatile int sessionIdsCounter = 0;

    private InMemoryDB() {
    }

    public synchronized static InMemoryDB getInstance() {
        if (inMemoryDB == null) {
            inMemoryDB = new InMemoryDB();
        }
        return inMemoryDB;
    }

    public void create(T t) {
        if (t instanceof User) {
            ((User) t).setId(++userIdsCounter);
            synchronized (users) {
                users.add((User) t);
            }

        } else if (t instanceof Movie) {
            ((Movie) t).setId(++movieIdsCounter);
            synchronized (movies) {
                movies.add((Movie) t);
            }

        } else if (t instanceof Ticket) {
            ((Ticket) t).setId(++ticketIdsCounter);
            synchronized (tickets) {
                tickets.add((Ticket) t);
            }
        } else if (t instanceof Hall) {
            ((Hall) t).setId(++hallIdsCounter);
            synchronized (halls) {
                halls.add((Hall) t);
            }
        } else if (t instanceof Session) {
            ((Session) t).setId(++sessionIdsCounter);
            synchronized (sessions) {
                sessions.add((Session) t);
            }
        } else throw new RuntimeException("Couldn't find object " + t.getClass());

    }

    public List<T> getAll(Class<T> t) {
        List<T> list = new LinkedList<>();
        if (t.equals(User.class)) {
            list = (List<T>) users;
        } else if (t.equals(Movie.class)) {
            list = (List<T>) movies;
        } else if (t.equals(Hall.class)) {
            list = (List<T>) halls;
        } else if (t.equals(Ticket.class)) {
            list = (List<T>) tickets;
        } else if (t.equals(Session.class)) {
            list = (List<T>) sessions;
        } else throw new RuntimeException("Couldn't find object " + t.getClass());
        return list;
    }

    public T update(T t) {
        T obj = null;
        if (t instanceof User) {
            synchronized (users) {
                User user = getUserById(((User) t).getId());
                user.setFirstName(((User) t).getFirstName());
                user.setLastName(((User) t).getLastName());
                user.setRole(((User) t).getRole());
                user.setDate(((User) t).getDate());
                user.setEmail(((User) t).getEmail());
                user.setLogin(((User) t).getLogin());
                user.setPassword(((User) t).getPassword());
                obj = (T) user;
            }
        } else if (t instanceof Movie) {
            synchronized (movies) {
                Movie movie = getMovieById(((Movie) t).getId());
                movie.setTitle(((Movie) t).getTitle());
                movie.setDescription(((Movie) t).getDescription());
                movie.setDuration(((Movie) t).getDuration());
                System.out.println(movie);
                obj = (T) movie;
            }
        } else if (t instanceof Hall) {
            synchronized (halls) {
                Hall hall = getHallById(((Hall) t).getId());
                hall.setName(((Hall) t).getName());
                hall.setRowCount(((Hall) t).getRowCount());
                hall.setColumnCount(((Hall) t).getColumnCount());
                obj = (T) hall;
            }
        } else if (t instanceof Ticket) {
            synchronized (tickets) {
                Ticket ticket = getTicketById(((Ticket) t).getId());
                ticket.setRow(((Ticket) t).getRow());
                ticket.setColumn(((Ticket) t).getColumn());
                obj = (T) ticket;
            }
        } else if (t instanceof Session) {
            synchronized (sessions) {
                Session session = getSessionById(((Session) t).getId());
                session.setMovie(((Session) t).getMovie());
                session.setDate(((Session) t).getDate());
                session.setHall(((Session) t).getHall());
                session.setTickets(((Session) t).getTickets());
                obj = (T) session;
            }
        } else throw new RuntimeException("Couldn't find object " + t.getClass());
        return obj;
    }

    public void delete(Class<T> t, K k) {
        if (t.equals(User.class)) {
            synchronized (users) {
                User user = getUserById((Integer) k);
                users.remove(user);
            }
        } else if (t.equals(Hall.class)) {
            synchronized (halls) {
                Hall hall = getHallById((Integer) k);
                halls.remove(hall);
            }
        } else if (t.equals(Movie.class)) {
            synchronized (movies) {
                Movie movie = getMovieById((Integer) k);
                movies.remove(movie);
            }
        } else if (t.equals(Ticket.class)) {
            synchronized (tickets) {
                Ticket ticket = getTicketById((Integer) k);
                tickets.remove(ticket);

            }
        } else if (t.equals(Session.class)) {
            synchronized (sessions) {
                Session session = getSessionById((Integer) k);
                sessions.remove(session);
            }
        } else throw new RuntimeException("Couldn't find object " + t.getClass());
    }

    public T get(Class<T> t, K id) {
        T obj = null;
        if (t.equals(User.class)) {
            User user = getUserById((Integer) id);
            obj = (T) user;
        } else if (t.equals(Movie.class)) {
            Movie movie = getMovieById((Integer) id);
            obj = (T) movie;
        } else if (t.equals(Ticket.class)) {
            Ticket ticket = getTicketById((Integer) id);
            obj = (T) ticket;
        } else if (t.equals(Hall.class)) {
            Hall hall = getHallById((Integer) id);
            obj = (T) hall;
        } else if (t.equals(Session.class)) {
            Session session = getSessionById((Integer) id);
            obj = (T) session;
        } else throw new RuntimeException("Couldn't find object " + t.getClass());
        return obj;
    }

    public User getUserById(Integer id) {
        if (users == null) throw new NoSuchElementException("List is empty");
        if (id < 1 & id > userIdsCounter) throw new NoSuchElementException("There is no such id");
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new NoSuchElementException("There is no such id");
    }

    public Ticket getTicketById(int id) {
        if (tickets == null) throw new NoSuchElementException("List is empty");
        if (id < 1 & id > ticketIdsCounter) throw new NoSuchElementException("There is no such id");
        for (Ticket ticket : tickets) {
            if (ticket.getId().equals(id)) {
                return ticket;
            }
        }
        throw new NoSuchElementException("There is no such id");
    }

    public Hall getHallById(int id) {
        if (halls == null) throw new NoSuchElementException("List is empty");
        if (id < 1 & id > hallIdsCounter) throw new NoSuchElementException("There is no such id");
        for (Hall hall : halls) {
            if (hall.getId().equals(id)) {
                return hall;
            }
        }
        throw new NoSuchElementException("There is no such id");
    }

    public Session getSessionById(int id) {
        if (sessions == null) throw new NoSuchElementException("List is empty");
        if (id < 1 & id > sessionIdsCounter) throw new NoSuchElementException("There is no such id");
        for (Session session : sessions) {
            if (session.getId().equals(id)) {
                return session;
            }
        }
        throw new NoSuchElementException("There is no such id");
    }

    public Movie getMovieById(int id) {

        if (movies == null) throw new NoSuchElementException("List is empty");
        if (id < 1 & id > movieIdsCounter) throw new NoSuchElementException("There is no such id");
        for (Movie movie : movies) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }

        throw new NoSuchElementException("There is no such id");
    }

    public boolean userLoginIsUnique(String login) {
        if (users == null) return true;
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return false;
            }
        }
        return true;
    }

    public User findUser(String login, String password) throws UserException {
        if (users == null) throw new NoSuchElementException("List is empty");
        if (login == null) throw new UserException("Login is empty");
        if (password == null) throw new UserException("Password is empty");
        for (User user : users) {
            if (user.getLogin().equals(login) & user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new NoSuchElementException("There is no such user");
    }

    public Ticket purchaseTicket(int row, int column, int sessionId) {
        synchronized (tickets) {
            if (findTicketBySessionId(row, column, sessionId) == null) {
                Ticket ticket = new Ticket();
                ticket.setId(++ticketIdsCounter);
                ticket.setRow(row);
                ticket.setColumn(column);
                synchronized (sessions) {
                    Session session = getSessionById(sessionId);
                    session.getTickets().add(ticket);
                }
                return ticket;
            }
        }
        throw new RuntimeException("Ticket is already sold out");
    }

    public void returnTicket(Integer id) {
        Ticket ticket = findTicketById(id);
        if (ticket != null) {
            synchronized (tickets) {
                tickets.remove(ticket);
            }

        } else throw new RuntimeException("This ticket wasn't sold out");
    }

    private Ticket findTicketById(Integer id) {
        if (tickets == null) throw new NoSuchElementException("List is empty");
        if (id < 1 & id > ticketIdsCounter) throw new NoSuchElementException("There is no such id");
        for (Ticket ticket : tickets) {
            if (ticket.getId().equals(id)) {
                return ticket;
            }
        }
        throw new NoSuchElementException("There is no such id");
    }


    private Ticket findTicketBySessionId(int row, int column, int sessionId) {
        Session session = getSessionById(sessionId);
        List<Ticket> tickets = session.getTickets();
        if (tickets == null) return null;
        for (Ticket ticket : tickets) {
            if (ticket.getRow().equals(row) && ticket.getColumn().equals(column)) {
                return ticket;
            }
        }
        return null;
    }

    public Session createSession(int movieId, int hallId, Date date) {
        Hall hall = getHallById(hallId);
        Movie movie = getMovieById(movieId);

        Session session = new Session();
        session.setId(++sessionIdsCounter);

        session.setMovie(movie);
        session.setHall(hall);
        session.setDate(date);
        sessions.add(session);
        return session;
    }

}
