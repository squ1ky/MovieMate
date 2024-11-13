package com.squiky.userservice.service;

import com.squiky.userservice.model.FavoriteMovie;
import com.squiky.userservice.model.WatchlistMovie;
import com.squiky.userservice.repository.FavoriteMovieRepository;
import com.squiky.userservice.repository.WatchlistMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieResponseHandler {

    private final FavoriteMovieRepository favoriteMovieRepository;
    private final WatchlistMovieRepository watchlistMovieRepository;

    @RabbitListener(queues = "${rabbitmq.movie-responses.queue.favorite-movies-queue}")
    public void handleAndSaveFavoriteMovie(Long[] userIdAndMovieId) {
        Long userId = userIdAndMovieId[0];
        Long movieDTO = userIdAndMovieId[1];

        FavoriteMovie favoriteMovie = FavoriteMovie.builder()
                .userId(userId)
                .movieId(movieDTO)
                .build();

        favoriteMovieRepository.save(favoriteMovie);
    }

    @RabbitListener(queues = "${rabbitmq.movie-responses.queue.watchlist-movies-queue}")
    public void handleAndSaveMovieToWatchlist(Long[] userIdAndMovieId) {
        Long userId = userIdAndMovieId[0];
        Long movieDTO = userIdAndMovieId[1];

        WatchlistMovie watchlistMovie = WatchlistMovie.builder()
                .userId(userId)
                .movieId(movieDTO)
                .build();

        watchlistMovieRepository.save(watchlistMovie);
    }
}
