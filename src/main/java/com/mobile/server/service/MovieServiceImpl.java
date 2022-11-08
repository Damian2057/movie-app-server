package com.mobile.server.service;

import com.google.gson.Gson;
import com.mobile.server.api.factory.ConnectionFactory;
import com.mobile.server.api.httpconnection.MovieApiConnection;
import com.mobile.server.configuration.MovieApiProperties;
import com.mobile.server.exception.types.ApiExceptions;
import com.mobile.server.model.Genre;
import com.mobile.server.model.Genres;
import com.mobile.server.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieApiProperties apiProperties;
    private final ConnectionFactory factory = new ConnectionFactory();

    @Override
    public Movie getMovie(String title) {
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
//        try(MovieApiConnection apiConnection = factory.build(apiProperties.getUrl(), apiProperties.getKey())) {
//            return null;
//        } catch (Exception e) {
//            throw new ApiExceptions.ConnectionException("Error connecting to movie api");
//        }
        return null;
    }

    @Override
    public List<Movie> getMovies(String genre) {
        return null;
    }

    @Override
    public Genre getGenre(int id) {
        return getSingleGenre(id);
    }

    @Override
    public Genre getGenre(String name) {
        return getSingleGenre(name);
    }

    @Override
    public List<Genre> getGenres() {
        return getGenreList();
    }

    private Genre getSingleGenre(String name) {
        return getGenreList().stream()
                .filter(genre -> genre.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() ->
                        new ApiExceptions.ParameterException(name + " the genre is not available"));
    }

    private Genre getSingleGenre(int id) {
        return getGenreList().stream()
                .filter(genre -> genre.getId().equals(id))
                .findAny()
                .orElseThrow(() ->
                        new ApiExceptions.ParameterException("the genre is not available"));
    }

    private List<Genre> getGenreList() {
        try(MovieApiConnection apiConnection = factory.build(apiProperties.getUrl(), apiProperties.getKey())) {
            apiConnection.setRequestMethod("GET");
            apiConnection.appendEndPoint("/genre/movie/list");
            apiConnection.buildRequest();
            String response = apiConnection.response();

            Gson gson = new Gson();
            Genres nameList = gson.fromJson(response, Genres.class);
            return nameList.getGenres();
        } catch (ApiExceptions.ConnectionException | IOException e) {
            throw new ApiExceptions.ConnectionException("Error connecting to movie api");
        }
    }

}
