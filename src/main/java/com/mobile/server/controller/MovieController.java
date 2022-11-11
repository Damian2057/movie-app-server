package com.mobile.server.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mobile.server.configuration.MovieApiProperties;
import com.mobile.server.controller.pojo.MoviesDto;
import com.mobile.server.controller.dto.UserDto;
import com.mobile.server.controller.mapper.Mapper;
import com.mobile.server.exception.types.ApiExceptions;
import com.mobile.server.model.Genre;
import com.mobile.server.model.User;
import com.mobile.server.service.MovieService;
import com.mobile.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieApiProperties apiProperties;

    /**
     * @return All available Genres in the system
     */
    @GetMapping("/getGenreList")
    public ResponseEntity<List<Genre>> getGenreList() throws IOException {
        return new ResponseEntity<>(movieService.getGenres(), HttpStatus.OK);
    }

    /**
     * adds genre to a specific user
     * @return affected user
     */
    @PutMapping("/addGenre/{name}")
    public ResponseEntity<UserDto> addGenre(HttpServletRequest request, @PathVariable(value = "name") String name) {
        Optional<User> user = userService.addGenreToUser(getUserFromHeader(request), movieService.getGenre(name));
        return new ResponseEntity<>(Mapper.mapUser(user.get()), HttpStatus.ACCEPTED);
    }

    /**
     * removes genre from specific user
     * @return affected user
     */
    @PutMapping("/removeGenre/{name}")
    public ResponseEntity<UserDto> removeGenre(HttpServletRequest request, @PathVariable(value = "name") String name) {
        Optional<User> user = userService.removeGenreToUser(getUserFromHeader(request), movieService.getGenre(name));
        return new ResponseEntity<>(Mapper.mapUser(user.get()), HttpStatus.ACCEPTED);
    }

    /**
     * Returns a list of the user's genres
     * @param request header
     * @return collection of genres
     */
    @GetMapping("/getUserGenreList")
    public ResponseEntity<Collection<Genre>> getUserGenreList(HttpServletRequest request) {
        return new ResponseEntity<>(getUserFromHeader(request).getFavoriteGenres(), HttpStatus.OK);
    }

    /**
     * adds genre list to a specific user
     * @return affected user
     */
    @PutMapping("/addGenreList")
    public ResponseEntity<?> addGenreList() {
        //TODO:?
        return ResponseEntity.ok().build();
    }

    @PutMapping("/addMovie/{name}")
    public ResponseEntity<?> addMovie(HttpServletRequest request, @PathVariable(value = "name") String name) {
        Optional<User> user = userService.addMovieToUser(getUserFromHeader(request), movieService.getMovieByName(name));
        return new ResponseEntity<>(Mapper.mapUser(user.get()), HttpStatus.ACCEPTED);
    }

    @PutMapping("/addMovieList")
    public ResponseEntity<?> addMovieList() {
        //TODO:?
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getMovie/{id}")
    public ResponseEntity<MoviesDto> getMovieByID(@PathVariable(value = "id") String id) {
        return new ResponseEntity<>(Mapper.mapMovie(movieService.getMovieByID(id), apiProperties.getImg()), HttpStatus.OK);
    }

    @GetMapping("/getMovieByGenre/{genre}/{page}")
    public ResponseEntity<List<MoviesDto>> getMovies(@PathVariable(value = "genre") String genre,
                                                     @PathVariable(value = "page") String page) {
        return new ResponseEntity<>(Mapper.mapMovies(movieService.getMoviesByGenre(genre, page), apiProperties.getImg()), HttpStatus.OK);
    }

    @PutMapping("/removeMovie")
    public ResponseEntity<?> removeMovie() {
        //TODO:?
        return ResponseEntity.ok().build();
    }

    private User getUserFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refresh_token = authorizationHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refresh_token);
            String username = decodedJWT.getSubject();
            return userService.getUser(username);
        } else {
            throw new ApiExceptions.InvalidBusinessArgumentException("request submitted without authorization");
        }
    }

}
