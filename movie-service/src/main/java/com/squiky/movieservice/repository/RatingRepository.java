package com.squiky.movieservice.repository;

import com.squiky.movieservice.model.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Rating.RatingId> {
    List<Rating> findAllByIdUserId(Long userId);
}
