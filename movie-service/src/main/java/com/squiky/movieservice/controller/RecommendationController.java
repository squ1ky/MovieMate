package com.squiky.movieservice.controller;

import com.squiky.movieservice.model.movie.Movie;
import com.squiky.movieservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public List<Movie> getRecommendations(@PathVariable Long userId) {
        return recommendationService.recommendMovies(userId);
    }
}
