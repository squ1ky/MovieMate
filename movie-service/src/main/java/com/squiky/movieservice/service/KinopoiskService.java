package com.squiky.movieservice.service;

import com.squiky.movieservice.model.Movie;
import com.squiky.movieservice.model.MovieResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
            log.warn("Movie not found for query: {}", movieName);
            return null;
        }
    }
}
