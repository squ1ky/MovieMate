package com.squiky.movieservice.service;

import com.squiky.movieservice.model.movie.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResponseMovieService {

    private final KinopoiskService kinopoiskService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.movie-responses.routing-key.favorite-movies-routing-key}")
    private String favoriteMoviesRoutingKey;
    @Value("${rabbitmq.movie-responses.routing-key.watchlist-movies-routing-key}")
    private String watchlistMoviesRoutingKey;

    @RabbitListener(queues = "${rabbitmq.movie-responses.queue.favorite-movies-queue}")
    public void sendFavoriteMovieIdIfExists(Long[] userIdAndMovieId) {
        Long movieId = userIdAndMovieId[1];

        Movie movie = kinopoiskService.findMovieById(movieId);
        if (movie != null) {
            rabbitTemplate.convertAndSend(favoriteMoviesRoutingKey, userIdAndMovieId);
        } else {
            log.warn("Movie not found with id {}", movieId);
        }
    }

    @RabbitListener(queues = "${rabbitmq.movie-responses.queue.watchlist-movies-queue}")
    public void sendWatchlistMovieIdIfExists(Long[] userIdAndMovieId) {
        Long movieId = userIdAndMovieId[1];

        Movie movie = kinopoiskService.findMovieById(movieId);
        if (movie != null) {
            rabbitTemplate.convertAndSend(watchlistMoviesRoutingKey, userIdAndMovieId);
        } else {
            log.warn("Movie not found with id {}", movieId);
        }
    }
}
