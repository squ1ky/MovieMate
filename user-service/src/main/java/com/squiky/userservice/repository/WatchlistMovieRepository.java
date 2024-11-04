package com.squiky.userservice.repository;

import com.squiky.userservice.model.WatchlistMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchlistMovieRepository extends JpaRepository<WatchlistMovie, Long> {
    List<WatchlistMovie> findAllByUserId(long userId);
    void deleteByUserIdAndMovieId(long userId, long movieId);
}
