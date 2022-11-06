package com.mobile.server.controller;

import com.mobile.server.controller.pojo.RoleToUserForm;
import com.mobile.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final UserService userService;

    @PutMapping("/addGenre")
    public ResponseEntity<?> addGenre(@RequestBody RoleToUserForm form) {
        //TODO:?
        return ResponseEntity.ok().build();
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

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/removeMovie")
    public ResponseEntity<?> removeMovie(@RequestBody RoleToUserForm form) {
        //TODO:?
        return ResponseEntity.ok().build();
    }
}
