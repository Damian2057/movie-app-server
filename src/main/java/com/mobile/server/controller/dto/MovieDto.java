package com.mobile.server.controller.dto;

import com.mobile.server.model.Genre;
import lombok.*;

import java.util.Collection;

@AllArgsConstructor
@Getter
@Setter
public class MovieDto {
    private int id;
    private String title;
    private String overview;
    private String original_language;
    private String release_date;
    private Collection<Genre> genres;
    private String poster_path;
    private Integer runtime;
    private String status;
    private Double vote_average;
}
