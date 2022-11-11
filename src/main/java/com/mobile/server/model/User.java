package com.mobile.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String emailAddress;
    private String password;
    private Set<Role> roles = new HashSet<>();
    private Set<Genre> favoriteGenres = new HashSet<>();
    private Set<Movie> favoriteMovies = new HashSet<>();
    private Set<Movie> notificationsMovie = new HashSet<>();

    public void addGenre(Genre genre) {
        favoriteGenres.add(genre);
    }

    public void removeGenre(Genre genre) {
        favoriteGenres.remove(genre);
    }

    public void addMovie(Movie movie) {
        favoriteMovies.add(movie);
    }

    public void removeMovie(Movie movie) {
        favoriteMovies.remove(movie);
    }

    public void addRemMovie(Movie movie) {
        notificationsMovie.add(movie);
    }

    public void removeRemMovie(Movie movie) {
        notificationsMovie.remove(movie);
    }

    public void addGenreList(List<Genre> genres) {
        favoriteGenres.addAll(genres);
    }

    public void addMovieList(List<Movie> movies) {
        favoriteMovies.addAll(movies);
    }

}
