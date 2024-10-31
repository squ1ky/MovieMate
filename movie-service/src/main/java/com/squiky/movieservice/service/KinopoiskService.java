package com.squiky.movieservice.service;

import com.squiky.movieservice.model.movie.Movie;
import com.squiky.movieservice.model.movie.MovieResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KinopoiskService {

    @Value("${header.api.key}")
    private String API_KEY;
    private final WebClient webClient;

    public KinopoiskService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://api.kinopoisk.dev/v1.4")
                .build();
    }

    @Cacheable(value = "movies", key = "#id", unless = "#result == null")
    public Movie findMovieById(long id) {
        Movie movie = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/" + id)
                        .build())
                .header("X-API-KEY", API_KEY)
                .retrieve()
                .bodyToMono(Movie.class)
                .block();

        if (movie == null) {
            log.warn("Movie not found for id: {}", id);
        }

        return movie;
    }

    @Cacheable(value = "movies", key = "#movieName", unless = "#result == null")
    public Movie findMovieByName(String movieName) {
        MovieResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/search")
                        .queryParam("query", movieName)
                        .build())
                .header("X-API-KEY", API_KEY)
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();

        if (response != null && !response.getDocs().isEmpty()) {
            return response.getDocs().getFirst();
        } else {
            log.warn("Movie not found for name: {}", movieName);
            return null;
        }
    }

    @Cacheable(value = "bestMovies", key = "'top250movies-' + #page + '-' + #limit")
    public List<Movie> findBestMovies(int page, int limit) {
        MovieResponse movieResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie")
                        .queryParam("lists", "top250")
                        .queryParam("sortField", "rating.kp")
                        .queryParam("sortType", "-1")
                        .queryParam("page", page)
                        .queryParam("limit", limit)
                        .build())
                .header("X-API-KEY", API_KEY)
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();

        List<Movie> movies = new ArrayList<>();
        if (movieResponse != null && movieResponse.getDocs() != null) {
            movies.addAll(movieResponse.getDocs());
        }

        return movies;
    }
}
