package com.squiky.movieservice.controller;

import com.squiky.movieservice.model.movie.Movie;
import com.squiky.movieservice.model.rating.RatingRequest;
import com.squiky.movieservice.service.KinopoiskService;
import com.squiky.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final KinopoiskService kinopoiskService;
    private final MovieService movieService;

    @GetMapping("/{id}")
    public Movie findMovieById(@PathVariable long id) {
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

    @PostMapping("/{movieId}/rate")
    public void rateMovie(@PathVariable long movieId, @RequestBody RatingRequest ratingRequest) {
        movieService.rateMovie(movieId, ratingRequest);
    }
}
