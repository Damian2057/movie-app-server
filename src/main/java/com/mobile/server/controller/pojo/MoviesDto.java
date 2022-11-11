package com.mobile.server.controller.pojo;

import com.mobile.server.model.Genre;
import lombok.*;

import java.util.Collection;

@AllArgsConstructor
@Getter
@Setter
public class MoviesDto {
    private int id;
    private String title;
    private String overview;
    private String original_language;
    private String release_date;
    private Collection<Genre> genres;
    private String poster_path;
    private String  runtime;
    private String status;
    private Double vote_average;
}
