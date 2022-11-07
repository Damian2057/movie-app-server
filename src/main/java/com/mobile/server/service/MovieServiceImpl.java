package com.mobile.server.service;

import com.mobile.server.api.factory.ConnectionFactory;
import com.mobile.server.api.httpconnection.MovieApiConnection;
import com.mobile.server.configuration.MovieApiProperties;
import com.mobile.server.model.Genre;
import com.mobile.server.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return null;
    }
}
