package com.mobile.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobile.server.model.Genre;
import com.mobile.server.model.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    Movie getMovie(String title);
    List<Movie> getMovies(String genre);
    List<Movie> getAllMovies();
    Genre getGenre(int id);
    Genre getGenre(String name);
    List<Genre> getGenres() throws IOException;
}
