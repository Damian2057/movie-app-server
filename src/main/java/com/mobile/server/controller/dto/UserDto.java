package com.mobile.server.controller.dto;

import com.mobile.server.controller.pojo.MoviesDto;
import com.mobile.server.model.Genre;
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
    private String email;
    private Collection<Genre> favoriteGenres;
    private Collection<MoviesDto> favoriteMovies;
    private Collection<MoviesDto> notificationsMovie;
}
