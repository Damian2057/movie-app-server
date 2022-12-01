package com.mobile.server.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Document
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private int id;
    private String title;
    private String overview;
    private String original_language;
    private String release_date;
    private Collection<Genre> genres = new ArrayList<>();
    private String poster_path;
    private String runtime;
    private String status;
    private Double vote_average;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return id == movie.id && title.equals(movie.title) &&
                Objects.equals(overview, movie.overview) &&
                Objects.equals(original_language, movie.original_language) &&
                Objects.equals(release_date, movie.release_date) &&
                Objects.equals(genres, movie.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, overview, original_language, release_date, genres);
    }
}
