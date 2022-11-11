package com.mobile.server.controller.mapper;

import com.mobile.server.controller.dto.UserDto;
import com.mobile.server.controller.pojo.MovieFormDto;
import com.mobile.server.controller.pojo.MoviesDto;
import com.mobile.server.model.Genre;
import com.mobile.server.model.Movie;
import com.mobile.server.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Mapper {

    public static List<UserDto> mapUsers(List<User> users) {
        return users.stream().map(user ->
                new UserDto(user.getId(),
                        user.getUsername(),
                        user.getFavoriteGenres(),
                        user.getFavoriteMovies(),
                        user.getReminderMovies()))
                .collect(Collectors.toList());
    }

    public static UserDto mapUser(User user) {
        return new UserDto(user.getId(),
                user.getUsername(),
                user.getFavoriteGenres(),
                user.getFavoriteMovies(),
                user.getReminderMovies());
    }

    public static MoviesDto mapMovie(Movie movie, String imgUrl) {
        return new MoviesDto(movie.getId(),
                movie.getTitle(),
                movie.getOverview(),
                movie.getOriginal_language(),
                movie.getRelease_date(),
                movie.getGenres(),
                imgUrl.replace("\"","") + movie.getPoster_path(),
                movie.getRuntime(),
                movie.getStatus(),
                movie.getVote_average());
    }

    public static List<MoviesDto> mapMovies(List<Movie> movies, String imgUrl) {
        return movies.stream().map(movie ->
                new MoviesDto(movie.getId(),
                        movie.getTitle(),
                        movie.getOverview(),
                        movie.getOriginal_language(),
                        movie.getRelease_date(),
                        movie.getGenres(),
                        imgUrl.replace("\"","") + movie.getPoster_path(),
                        movie.getRuntime(),
                        movie.getStatus(),
                        movie.getVote_average())).collect(Collectors.toList());
    }

    public static List<Movie> mapMoviesForm(List<MovieFormDto> movies, List<Genre> genres) {
        return movies.stream().map(movie ->
                new Movie(movie.getId(),
                        movie.getTitle(),
                        movie.getOverview(),
                        movie.getOriginal_language(),
                        movie.getRelease_date(),
                        mapGenresId(movie.getGenre_ids(), genres),
                        movie.getPoster_path(),
                        movie.getRuntime(),
                        movie.getStatus(),
                        movie.getVote_average())).collect(Collectors.toList());
    }

    public static List<Genre> mapGenresId(Collection<Integer> genres_id, List<Genre> genres) {
        return genres_id.stream().map(genre -> genres.stream().parallel().filter(x ->
                Objects.equals(x.getId(), genre)).findFirst().get()).collect(Collectors.toList());
    }

}
