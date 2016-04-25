package com.cinema.service.api;
import com.cinema.dto.MovieDTO;
import com.cinema.exception.MovieExistException;
import com.cinema.model.Movie;

import java.util.List;

public interface MovieService extends Service<MovieDTO, Integer> {
    void checkMovie(String title) throws MovieExistException;
}
