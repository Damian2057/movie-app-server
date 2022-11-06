package com.mobile.server.service;

import com.mobile.server.model.Genre;
import com.mobile.server.model.Movie;

import java.util.List;

public interface MovieService {
    Movie getMovie(String title);
    List<Movie> getMovies(String genre);

    Genre getGenre(int id);
    Genre getGenre(String name);
    List<Genre> getGenres();
}
