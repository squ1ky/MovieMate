package com.squiky.movieservice.repository;

import com.squiky.movieservice.model.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Rating.RatingId> {
}
