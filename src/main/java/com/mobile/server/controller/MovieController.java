package com.mobile.server.controller;

import com.mobile.server.controller.pojo.RoleToUserForm;
import com.mobile.server.model.Genre;
import com.mobile.server.service.MovieService;
import com.mobile.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;

    @PutMapping("/addGenre")
    public ResponseEntity<?> addGenre(@RequestBody RoleToUserForm form) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getGenre")
    public ResponseEntity<?> getGenre() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getGenreList")
    public ResponseEntity<List<Genre>> getGenreList() throws IOException {
        return new ResponseEntity<>(movieService.getGenres(), HttpStatus.OK);
    }

    @PutMapping("/addGenreList")
    public ResponseEntity<?> addGenreList(@RequestBody RoleToUserForm form) {
        //TODO:?
        return ResponseEntity.ok().build();
    }

    @PutMapping("/removeGenre")
    public ResponseEntity<?> removeGenre(@RequestBody RoleToUserForm form) {
        //TODO:?
        return ResponseEntity.ok().build();
    }

    @PutMapping("/addMovie")
    public ResponseEntity<?> addMovie(@RequestBody RoleToUserForm form) {
        //TODO:?
        return ResponseEntity.ok().build();
    }

    @PutMapping("/addMovieList")
    public ResponseEntity<?> addMovieList(@RequestBody RoleToUserForm form) {
        //TODO:?
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getMovie")
    public ResponseEntity<?> ping() {
        movieService.getMovie("xd");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/removeMovie")
    public ResponseEntity<?> removeMovie(@RequestBody RoleToUserForm form) {
        //TODO:?
        return ResponseEntity.ok().build();
    }
}
