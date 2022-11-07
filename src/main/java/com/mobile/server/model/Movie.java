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
    private String description;
    private String releaseDate;
    private Collection<Genre> genres = new ArrayList<>();
    private String posterPath;
    private Integer runtime;
    private Double voteAverage;

}
