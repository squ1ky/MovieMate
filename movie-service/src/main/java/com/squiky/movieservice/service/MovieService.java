package com.squiky.movieservice.service;

import com.squiky.movieservice.model.rating.Rating;
import com.squiky.movieservice.model.rating.RatingRequest;
import com.squiky.movieservice.repository.RatingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final RatingRepository ratingRepository;

    @Transactional
    public void rateMovie(long movieId, RatingRequest ratingRequest) {
        Rating.RatingId ratingId = new Rating.RatingId(ratingRequest.getUserId(), movieId);
        Rating rating = ratingRepository.findById(ratingId).orElse(new Rating());

        rating.setId(ratingId);
        rating.setRating(ratingRequest.getRating());

        ratingRepository.save(rating);
    }
}
