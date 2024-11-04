package com.squiky.userservice.repository;

import com.squiky.userservice.model.FavoriteMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Long> {
    List<FavoriteMovie> findAllByUserId(Long userId);
    void deleteByUserIdAndMovieId(Long userId, Long movieId);
}
