package com.mobile.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobile.server.model.Genre;
import com.mobile.server.model.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    List<Movie> getMovieSearch(String query, String page);
    List<Movie> getMoviesSearch(String genre);
    List<Movie> getAllMovies(String page);
    Movie getMovieByID(String id);
    Genre getGenre(int id);
    Genre getGenre(String name);
    List<Genre> getGenres() throws IOException;
    Movie getMovieByName(String name);

    List<Movie> getMoviesByGenre(String genre, String page);
}
