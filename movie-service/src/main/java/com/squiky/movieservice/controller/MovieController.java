package com.squiky.movieservice.controller;

import com.squiky.movieservice.model.Movie;
import com.squiky.movieservice.service.KinopoiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final KinopoiskService kinopoiskService;

    @GetMapping("/{id}")
    public Movie findMovieById(@PathVariable int id) {
        return kinopoiskService.findMovieById(id);
    }

    @GetMapping
    public Movie findMovieByName(@RequestParam String query) {
        return kinopoiskService.findMovieByName(query);
    }

    @GetMapping("/best")
    public List<Movie> findTop250Movies(@RequestParam int page, @RequestParam int limit) {
        return kinopoiskService.findBestMovies(page, limit);
    }
}
