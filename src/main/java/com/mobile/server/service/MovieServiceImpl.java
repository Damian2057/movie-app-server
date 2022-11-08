package com.mobile.server.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieApiProperties apiProperties;
    private ConnectionFactory factory = new ConnectionFactory();

    @Override
    public Movie getMovie(String title) {
//        try {
//            MovieApiConnection movieApiConnection = new MovieApiConnection(apiProperties.getUrl(), apiProperties.getKey());
//            String xdd = xd.response();
//            System.out.println(xdd);
//        } catch (Exception e) {
//            System.out.println("BLAD");
//        }
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        try(MovieApiConnection apiConnection = factory.build(apiProperties.getUrl(), apiProperties.getKey())) {
            return null;
        } catch (Exception e) {
            throw new ApiExceptions.ConnectionException("Error connecting to movie api");
        }
    }

    @Override
    public List<Movie> getMovies(String genre) {
        return null;
    }

    @Override
    public Genre getGenre(int id) {
        return null;
    }

    @Override
    public Genre getGenre(String name) {
        return null;
    }

    @Override
    public List<Genre> getGenres() {
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
