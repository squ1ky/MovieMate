package com.squiky.movieservice.service;

import com.squiky.movieservice.model.movie.Movie;
import com.squiky.movieservice.model.rating.Rating;
import com.squiky.movieservice.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final KinopoiskService kinopoiskService;
    private final RatingRepository ratingRepository;

    public List<Movie> recommendMovies(long userId) {
        List<Movie> recommendedMovies = new ArrayList<>();

        List<Rating> userRatings = findUserRatings(userId);
        userRatings.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));

        for (Rating rating : userRatings) {
            if (isGoodRating(rating.getRating())) {
                long movieId = rating.getId().getMovieId();
                Movie movie = kinopoiskService.findMovieById(movieId);
                List<Movie> similarMovies = movie.getSimilarMovies();

                for (Movie similarMovie : similarMovies) {
                    Movie fullMovie = kinopoiskService.findMovieById(similarMovie.getId());
                    recommendedMovies.add(fullMovie);
                }
            }
        }

        return recommendedMovies;
    }


    private List<Rating> findUserRatings(long userId) {
        return ratingRepository.findAllByIdUserId(userId);
    }

    private boolean isGoodRating(int rating) {
        return rating >= 6;
    }
}
