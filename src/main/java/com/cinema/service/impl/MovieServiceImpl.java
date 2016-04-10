package com.cinema.service.impl;

import com.cinema.Transformer;
import com.cinema.dao.api.MovieDAO;
import com.cinema.dao.impl.MovieDAODB;
import com.cinema.dao.impl.MovieDAOImpl;
import com.cinema.dao.storage.Configuration;
import com.cinema.dto.MovieDTO;
import com.cinema.model.Movie;
import com.cinema.service.api.MovieService;

import java.util.LinkedList;
import java.util.List;

public final class MovieServiceImpl implements MovieService {
    private static MovieServiceImpl movieService;
    boolean isDB = Boolean.valueOf(Configuration.getInstance().getProperty(Configuration.DB));

    public synchronized static MovieServiceImpl getInstance() {
        if (movieService == null) {
            movieService = new MovieServiceImpl();
        }
        return movieService;
    }

    private MovieServiceImpl() {
    }

    @Override
    public List<MovieDTO> getAll() {
        List<MovieDTO> movieDTOs = new LinkedList<>();
        if (!isDB) {
            MovieDAO movieDAO = MovieDAOImpl.getInstance();
            List<Movie> movies = movieDAO.getAll();
            movieDTOs = Transformer.listMovieToListMovieDTO(movies);
        }else{
            MovieDAO movieDAO = MovieDAODB.getInstance();
            List<Movie> movies = movieDAO.getAll();
            movieDTOs = Transformer.listMovieToListMovieDTO(movies);
        }
        return movieDTOs;
    }

    @Override
    public void create(MovieDTO movieDTO) {
        if (!isDB) {
            MovieDAO movieDAO = MovieDAOImpl.getInstance();
            Movie movie = Transformer.movieDTOTMovie(movieDTO);
            movieDAO.create(movie);
        }else{
            MovieDAO movieDAO = MovieDAODB.getInstance();
            Movie movie = Transformer.movieDTOTMovie(movieDTO);
            movieDAO.create(movie);
        }

    }

    @Override
    public MovieDTO get(Integer id) {
        MovieDTO movieDTO = null;
        if (!isDB) {
            MovieDAO movieDAO = MovieDAOImpl.getInstance();
            Movie movie = null;
            movie = (Movie) movieDAO.get(id);
            movieDTO = Transformer.movieToMovieDto(movie);

        }else{
            MovieDAO movieDAO = MovieDAODB.getInstance();
            Movie movie = null;
            movie = (Movie) movieDAO.get(id);
            movieDTO = Transformer.movieToMovieDto(movie);
        }
        return movieDTO;
    }

    @Override
    public MovieDTO update(MovieDTO movieDTO) {
        MovieDTO movieDTONew = null;
        if (!isDB) {
            MovieDAO movieDAO = MovieDAOImpl.getInstance();
            Movie movie = (Movie) movieDAO.update(Transformer.movieDTOTMovie(movieDTO));
            movieDTONew = Transformer.movieToMovieDto(movie);
        }else{
            MovieDAO movieDAO = MovieDAODB.getInstance();
            Movie movie = (Movie) movieDAO.update(Transformer.movieDTOTMovie(movieDTO));
            movieDTONew = Transformer.movieToMovieDto(movie);
        }
        return movieDTONew;
    }

    @Override
    public void delete(Integer id) {
        if (!isDB) {
            MovieDAO movieDAO = MovieDAOImpl.getInstance();
            movieDAO.delete(id);
        }else {
            MovieDAO movieDAO = MovieDAODB.getInstance();
            movieDAO.delete(id);
        }
    }
}

