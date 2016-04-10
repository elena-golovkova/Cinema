package com.cinema.dao.impl;

import com.cinema.dao.api.MovieDAO;
import com.cinema.model.Movie;

public class MovieDAODB extends CrudDAODataBase<Movie, Integer> implements MovieDAO<Movie, Integer> {
    private static MovieDAODB movieDAO;

    public synchronized static MovieDAODB getInstance() {

        if (movieDAO == null) {
            movieDAO = new MovieDAODB(Movie.class);

        }
        return movieDAO;
    }

    private MovieDAODB(Class type) {
        super(type);
    }

}
