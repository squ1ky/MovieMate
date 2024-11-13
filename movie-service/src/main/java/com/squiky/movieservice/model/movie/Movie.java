package com.squiky.movieservice.model.movie;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Movie {
    private long id;
    private String name;
    private String description;
    private String type;
    private int year;
    private List<Genre> genres;
    private List<Movie> similarMovies;

    @Getter
    @Setter
    public static class Genre {
        private String name;
    }
}
