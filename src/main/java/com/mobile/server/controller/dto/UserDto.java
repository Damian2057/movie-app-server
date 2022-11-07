package com.mobile.server.controller.dto;

import com.mobile.server.model.Genre;
import com.mobile.server.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String id;
    private String username;
    private Collection<Genre> favoriteGenres;
    private Collection<Movie> favoriteMovies;
    private Collection<Movie> reminderMovies;
}
