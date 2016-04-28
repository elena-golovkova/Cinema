package com.cinema.dao.api;

import com.cinema.exception.MovieExistException;
import com.cinema.model.Movie;
import java.util.List;

public interface MovieDAO<Movie, Integer> extends Dao<Movie, Integer> {
    void checkMovie(String title) throws MovieExistException;
    void checkMovieIfNotExist(Integer id) throws MovieExistException;

}
