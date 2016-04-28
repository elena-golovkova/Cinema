package com.cinema.dao.impl;

import com.cinema.dao.api.MovieDAO;
import com.cinema.exception.MovieExistException;
import com.cinema.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDAODB extends CrudDAODataBase<Movie, Integer> implements MovieDAO<Movie, Integer> {
    private static MovieDAODB movieDAO;
    private static final String SELECT_MOVIE = "select * from movie where title = ?";
    private static final String SELECT_MOVIE_BY_ID = "select * from movie where id = ?";

    public synchronized static MovieDAODB getInstance() {

        if (movieDAO == null) {
            movieDAO = new MovieDAODB(Movie.class);

        }
        return movieDAO;
    }

    private MovieDAODB(Class type) {
        super(type);
    }

    @Override
    public void checkMovie(String title) throws MovieExistException {
        Movie movie = null;
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_MOVIE);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDescription(resultSet.getString("description"));
                movie.setDuration(resultSet.getLong("duration"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (movie != null) throw new MovieExistException();
    }

    @Override
    public void checkMovieIfNotExist(Integer id) throws MovieExistException {
        Movie movie = null;
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_MOVIE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDescription(resultSet.getString("description"));
                movie.setDuration(resultSet.getLong("duration"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (movie == null) throw new MovieExistException();
    }
}
