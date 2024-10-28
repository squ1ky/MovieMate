package com.squiky.movieservice.controller;

import com.squiky.movieservice.model.Movie;
import com.squiky.movieservice.service.KinopoiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final KinopoiskService kinopoiskService;

    @GetMapping
    public Movie getMovie(@RequestParam String query) {
        return kinopoiskService.findMovieByName(query);
    }
}
