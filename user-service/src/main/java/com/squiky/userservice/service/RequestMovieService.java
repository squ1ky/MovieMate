package com.squiky.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestMovieService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.movie-requests.routing-key.favorite-movies-routing-key}")
    private String favoriteMovieRequestsRoutingKey;
    @Value("${rabbitmq.movie-requests.routing-key.watchlist-movies-routing-key}")
    private String watchlistMovieRequestsRoutingKey;

    public void requestFavoriteMovieById(Long userId, Long movieId) {
        Long[] userIdAndMovieId = new Long[]{userId, movieId};
        rabbitTemplate.convertAndSend(favoriteMovieRequestsRoutingKey, userIdAndMovieId);
    }

    public void requestWatchlistMovieById(Long userId, Long movieId) {
        Long[] userIdAndMovieId = new Long[]{userId, movieId};
        rabbitTemplate.convertAndSend(watchlistMovieRequestsRoutingKey, userIdAndMovieId);
    }
}
