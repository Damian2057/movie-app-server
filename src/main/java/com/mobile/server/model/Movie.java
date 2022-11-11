package com.mobile.server.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Document
@Data
@Getter
@Setter
public class Movie {

    private int id;
    private String title;
    private String overview;
    private String original_language;
    private String release_date;
    private Collection<Genre> genres = new ArrayList<>();
    private String poster_path;
    private Integer runtime;
    private String status;
    private Double vote_average;
}
