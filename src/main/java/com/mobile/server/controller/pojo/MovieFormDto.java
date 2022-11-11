package com.mobile.server.controller.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document
@Data
@Getter
@Setter
public class MovieFormDto {
    private int id;
    private String title;
    private String overview;
    private String original_language;
    private String release_date;
    private Collection<Integer> genre_ids;
    private String poster_path;
    private String runtime;
    private String status;
    private Double vote_average;
}
