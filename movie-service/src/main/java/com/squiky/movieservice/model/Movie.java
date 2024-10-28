package com.squiky.movieservice.model;

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
}
