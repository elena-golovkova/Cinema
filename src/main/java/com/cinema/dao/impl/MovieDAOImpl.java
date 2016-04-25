package com.cinema.dao.impl;

import com.cinema.dao.api.MovieDAO;
import com.cinema.exception.MovieExistException;
import com.cinema.model.Movie;

public class MovieDAOImpl extends AbstractDAOInMemory<Movie, Integer> implements MovieDAO<Movie, Integer> {

    private static MovieDAOImpl movieDAO;

    public synchronized static MovieDAOImpl getInstance() {

        if (movieDAO == null) {
            movieDAO = new MovieDAOImpl(Movie.class);

        }
        return movieDAO;
    }
    private MovieDAOImpl(Class type) {
        super(type);
    }

    @Override
    public void checkMovie(String title) throws MovieExistException {

    }
}
