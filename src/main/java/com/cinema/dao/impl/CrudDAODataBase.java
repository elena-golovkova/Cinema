package com.cinema.dao.impl;

import com.cinema.dao.api.Dao;
import com.cinema.dao.api.UserDAO;
import com.cinema.dao.storage.LocalDatePersistenceConverter;
import com.cinema.datasource.DataSource;
import com.cinema.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class CrudDAODataBase<T, Integer> implements Dao<T, Integer> {

    private Class<T> type;


    public static final String MOVIE = "movie";
    public static final String USER = "user";
    public static final String HALL = "hall";
    public static final String TICKET = "ticket";
    public static final String SESSION = "session";
    private static Map<Object, String> stringMap = new HashMap<>();
    protected DataSource instance = DataSource.getInstance();
    LocalDatePersistenceConverter converter = new LocalDatePersistenceConverter();

    private final String SELECT_ALL = "Select * from %s order by id desc";
    private final String FIND_BY_ID = "Select * from %s where id = ?";
    private final String INSERT = "Insert into %s %s";
    private final String DELETE = "Delete from %s where id = ?";
    private final String UPDATE = "Update %s set %s";

    public CrudDAODataBase(Class<T> type) {
        this.type = type;
        stringMap.put(Movie.class, MOVIE);
        stringMap.put(User.class, USER);
        stringMap.put(Ticket.class, TICKET);
        stringMap.put(Hall.class, HALL);
        stringMap.put(Session.class, SESSION);
    }


    public void delete(Integer id) {
        String sql = String.format(DELETE, stringMap.get(type));
        Connection connection = instance.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (java.lang.Integer) id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<T> getAll() {
        String sql = String.format(SELECT_ALL, stringMap.get(type));
        Connection connection = instance.getConnection();
        List result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public T get(Integer id) {
        String sql = String.format(FIND_BY_ID, stringMap.get(type));
        Connection connection = instance.getConnection();
        List result = null;
        T entity = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (java.lang.Integer) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result.size() > 0) {
            entity = (T) result.get(0);
        } else throw new RuntimeException("Wrong index");
        return entity;
    }


    public void create(T entity) {
        Connection connection = instance.getConnection();
        try {
            PreparedStatement preparedStatement = createInsertStatement(connection, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public T update(T entity) {
        Connection connection = instance.getConnection();
        T obj = null;
        Integer id = null;

        try {
            PreparedStatement preparedStatement = createUpdateStatement(connection, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        switch (stringMap.get(type)) {
            case MOVIE:
                id = (Integer) ((Movie) entity).getId();
                obj = get(id);
                break;
            case USER:
                id = (Integer) ((User) entity).getId();
                obj = get(id);
                break;
            case TICKET:
                id = (Integer) ((Ticket) entity).getId();
                obj = get(id);
                break;
            case HALL:
                id = (Integer) ((Hall) entity).getId();
                obj = get(id);
                break;
            case SESSION:
                id = (Integer) ((Session) entity).getId();
                obj = get(id);
                break;
        }
        return obj;
    }

    private PreparedStatement createInsertStatement(Connection connection, T entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = null;

        switch (stringMap.get(type)) {
            case MOVIE:
                Movie movie = (Movie) entity;
                sql = String.format(INSERT, stringMap.get(type), " (title, description, duration) values (?,?,?)");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, movie.getTitle());
                preparedStatement.setString(2, movie.getDescription());
                preparedStatement.setLong(3, movie.getDuration());
                break;
            case USER:
                UserDAO userDAO = UserDAODB.getInstance();
                User user = (User) entity;
                sql = String.format(INSERT, stringMap.get(type), " (first_name, last_name, email, login, password, date, role) values (?,?,?,?,?,?,?)");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getLogin());
                preparedStatement.setString(5, user.getPassword());
                Date date = converter.convertToDatabaseColumn(user.getDate());
                preparedStatement.setDate(6, date);
                preparedStatement.setString(7, String.valueOf(user.getRole()));
                break;
            case HALL:
                Hall hall = (Hall) entity;
                sql = String.format(INSERT, stringMap.get(type), " (name, row_count, column_count) values (?,?,?)");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, hall.getName());
                preparedStatement.setInt(2, hall.getRowCount());
                preparedStatement.setInt(3, hall.getColumnCount());
                break;
            case TICKET:
                Ticket ticket = (Ticket) entity;
                sql = String.format(INSERT, stringMap.get(type), " (row, place) values (?,?)");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, ticket.getRow());
                preparedStatement.setInt(2, ticket.getColumn());
                break;
            case SESSION:
                Session session = (Session) entity;
                //Timestamp dateSession = converterLocalDateToTimestamp.convertToDatabaseColumn(session.getDate());
                java.lang.Integer movieId = session.getMovie() == null ? null : session.getMovie().getId();
                java.lang.Integer hallId = session.getHall() == null ? null : session.getHall().getId();
                sql = String.format(INSERT, stringMap.get(type), " (date, hall_id, movie_id) values (?,?,?)");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setTimestamp(1, new Timestamp(session.getDate().getTime()));
                preparedStatement.setInt(2, hallId);
                preparedStatement.setInt(3, movieId);
                break;
        }

        return preparedStatement;
    }

    private PreparedStatement createUpdateStatement(Connection connection, T entity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = null;

        switch (stringMap.get(type)) {
            case MOVIE:
                Movie movie = (Movie) entity;
                sql = String.format(UPDATE, stringMap.get(type), " title = ?, description = ?, duration = ? where id = ?");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, movie.getTitle());
                preparedStatement.setString(2, movie.getDescription());
                preparedStatement.setLong(3, movie.getDuration());
                preparedStatement.setInt(4, movie.getId());
                break;
            case USER:
                User user = (User) entity;
                Date date = converter.convertToDatabaseColumn(user.getDate());
                sql = String.format(UPDATE, stringMap.get(type), " first_name = ?, last_name = ?, email = ?, login = ?, password = ?, date = ?, role = ? where id = ?");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getLogin());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setDate(6, date);
                preparedStatement.setString(7, String.valueOf(user.getRole()));
                preparedStatement.setInt(8, user.getId());
                break;
            case HALL:
                Hall hall = (Hall) entity;
                sql = String.format(UPDATE, stringMap.get(type), " name = ?, row_count = ?, column_count = ? where id = ?");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, hall.getName());
                preparedStatement.setInt(2, hall.getRowCount());
                preparedStatement.setInt(3, hall.getColumnCount());
                preparedStatement.setInt(4, hall.getId());
                break;
            case TICKET:
                Ticket ticket = (Ticket) entity;
                sql = String.format(UPDATE, stringMap.get(type), " row = ?, place = ? where id = ?");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, ticket.getRow());
                preparedStatement.setInt(2, ticket.getColumn());
                preparedStatement.setInt(3, ticket.getId());
                break;
            case SESSION:
                Session session = (Session) entity;
                java.lang.Integer movieId = session.getMovie() == null ? null : session.getMovie().getId();
                java.lang.Integer hallId = session.getHall() == null ? null : session.getHall().getId();

                sql = String.format(UPDATE, stringMap.get(type), " date = ?, hall_id = ?, movie_id = ? where id = ?");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setTimestamp(1, new Timestamp(session.getDate().getTime()));
                preparedStatement.setInt(2, hallId);
                preparedStatement.setInt(3, movieId);
                preparedStatement.setInt(4, session.getId());
                break;
        }
        return preparedStatement;
    }



    private List<T> readAll(ResultSet resultSet) throws SQLException {
        List<T> result = new LinkedList<>();

        while (resultSet.next()) {
            switch (stringMap.get(type)) {
                case MOVIE:
                    Movie movie = createMovie(resultSet);
                    result.add((T) movie);
                    break;
                case USER:
                    User user = createUser(resultSet);
                    result.add((T) user);
                    break;
                case HALL:
                    Hall hall = createHall(resultSet);
                    result.add((T) hall);
                    break;
                case TICKET:
                    Ticket ticket = createTicket(resultSet);
                    result.add((T) ticket);
                    break;
                case SESSION:
                    Session session = createSession(resultSet);
                    result.add((T) session);

            }
        }
        return result;
    }


    private Session createSession(ResultSet resultSet) throws SQLException {
        Session session = new Session();
        session.setId(resultSet.getInt("id"));
        session.setDate(resultSet.getTimestamp("date"));
        return session;
    }

    private Ticket createTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getInt("id"));
        ticket.setRow(resultSet.getInt("row"));
        ticket.setColumn(resultSet.getInt("place"));
        return ticket;
    }

    private Movie createMovie(ResultSet resultSet) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("id"));
        movie.setTitle(resultSet.getString("title"));
        movie.setDescription(resultSet.getString("description"));
        movie.setDuration(resultSet.getLong("duration"));
        return movie;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        LocalDate ld = converter.convertToEntityAttribute(resultSet.getDate("date"));
        Role role = Role.valueOf(resultSet.getString("role"));
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setDate(ld);
        user.setRole(role);
        return user;
    }

    private Hall createHall(ResultSet resultSet) throws SQLException {
        Hall hall = new Hall();
        hall.setId(resultSet.getInt("id"));
        hall.setName(resultSet.getString("name"));
        hall.setRowCount(resultSet.getInt("row_count"));
        hall.setColumnCount(resultSet.getInt("column_count"));
        return hall;
    }

}
