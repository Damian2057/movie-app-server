package com.mobile.server.service;

import com.google.gson.Gson;
import com.mobile.server.api.factory.ConnectionFactory;
import com.mobile.server.api.httpconnection.MovieApiConnection;
import com.mobile.server.configuration.MovieApiProperties;
import com.mobile.server.controller.mapper.Mapper;
import com.mobile.server.controller.pojo.Genres;
import com.mobile.server.controller.pojo.MovieForm;
import com.mobile.server.exception.types.ApiExceptions;
import com.mobile.server.model.Genre;
import com.mobile.server.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieApiProperties apiProperties;
    private final ConnectionFactory factory = new ConnectionFactory();

    @Override
    public List<Movie> getMovieSearch(String query, String page) {
        try(MovieApiConnection apiConnection = factory.build(apiProperties.getUrl(), apiProperties.getKey())) {
            apiConnection.setRequestMethod("GET");
            apiConnection.appendEndPoint("/search/movie");
            apiConnection.appendParam("page=" + page);
            apiConnection.appendParam("query=" + query);
            apiConnection.buildRequest();
            String response = apiConnection.response();

            Gson gson = new Gson();
//            List<Movie> nameList = gson.fromJson(response, Genres.class);
//            return nameList.getGenres();
        } catch (ApiExceptions.ConnectionException | IOException e) {
            throw new ApiExceptions.ConnectionException("Error connecting to movie api");
        }
        return null;
    }

    @Override
    public List<Movie> getAllMovies(String page) {
//        try(MovieApiConnection apiConnection = factory.build(apiProperties.getUrl(), apiProperties.getKey())) {
//            apiConnection.setRequestMethod("GET");
//            apiConnection.appendEndPoint("/genre/movie/list");
//            apiConnection.buildRequest();
//            String response = apiConnection.response();
//
//            Gson gson = new Gson();
//            Genres nameList = gson.fromJson(response, Genres.class);
//            return nameList.getGenres();
//        } catch (ApiExceptions.ConnectionException | IOException e) {
//            throw new ApiExceptions.ConnectionException("Error connecting to movie api");
//        }
        return null;
    }

    @Override
    public Movie getMovieByID(String id) {
        try(MovieApiConnection apiConnection = factory.build(apiProperties.getUrl(), apiProperties.getKey())) {
            apiConnection.setRequestMethod("GET");
            apiConnection.appendEndPoint("/movie/" + id);
            apiConnection.buildRequest();
            String response = apiConnection.response();

            Gson gson = new Gson();
            return gson.fromJson(response, Movie.class);
        } catch (ApiExceptions.ConnectionException | IOException e) {
            throw new ApiExceptions.ConnectionException("Error connecting to movie api");
        }
    }

    @Override
    public List<Movie> getMoviesSearch(String genre) {
        return null;
    }

    @Override
    public Genre getGenre(int id) {
        return getSingleGenreById(id);
    }

    @Override
    public Genre getGenre(String name) {
        return getSingleGenreById(name);
    }

    @Override
    public List<Genre> getGenres() {
        return getGenreList();
    }

    @Override
    public Movie getMovieByName(String name) {
        return getMovieByNameInternal(name);
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre, String page) {
        try(MovieApiConnection apiConnection = factory.build(apiProperties.getUrl(), apiProperties.getKey())) {
            apiConnection.setRequestMethod("GET");
            apiConnection.appendEndPoint("/discover/movie");
            apiConnection.appendParam("page=" + page);
            apiConnection.appendParam("with_genres=" + getSingleGenreByName(genre));
            apiConnection.buildRequest();
            String response = apiConnection.response();

            Gson gson = new Gson();
            MovieForm movieForm = gson.fromJson(response, MovieForm.class);
            return Mapper.mapMoviesForm(movieForm.getResults(), getGenreList());
        } catch (ApiExceptions.ConnectionException | IOException e) {
            throw new ApiExceptions.ConnectionException("Error connecting to movie api");
        }
    }

    @Override
    public List<Genre> getGenreList(List<String> genres) {
        return genres.stream().map(this::getSingleGenreByName).collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMovieList(List<String> titles) {
        return titles.stream().map(this::getMovieByName).collect(Collectors.toList());
    }

    private Genre getSingleGenreById(String name) {
        return getGenreList().stream()
                .filter(genre -> genre.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() ->
                        new ApiExceptions.ParameterException(name + " the genre is not available"));
    }

    private Genre getSingleGenreById(int id) {
        return getGenreList().stream()
                .filter(genre -> genre.getId().equals(id))
                .findAny()
                .orElseThrow(() ->
                        new ApiExceptions.ParameterException("the genre is not available"));
    }

    private Genre getSingleGenreByName(String name) {
        return getGenreList().stream()
                .filter(genre -> genre.getName().equals(name))
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

    private Movie getMovieByNameInternal(String name) {
        try(MovieApiConnection apiConnection = factory.build(apiProperties.getUrl(), apiProperties.getKey())) {
            apiConnection.setRequestMethod("GET");
            apiConnection.appendEndPoint("/search/movie");
            apiConnection.appendParam("query=" + name);
            apiConnection.buildRequest();
            var response = apiConnection.response();
            Gson gson = new Gson();
            MovieForm movieForm = gson.fromJson(response, MovieForm.class);
            return Mapper.mapMovieForm(movieForm.getResults().get(0), getGenreList());
        } catch (ApiExceptions.ConnectionException | IOException e) {
            throw new ApiExceptions.ConnectionException("Error connecting to movie api");
        }
    }

}
