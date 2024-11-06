package com.squiky.userservice.service;

import com.squiky.userservice.model.FavoriteMovie;
import com.squiky.userservice.model.User;
import com.squiky.userservice.model.WatchlistMovie;
import com.squiky.userservice.repository.FavoriteMovieRepository;
import com.squiky.userservice.repository.UserRepository;
import com.squiky.userservice.repository.WatchlistMovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FavoriteMovieRepository favoriteMovieRepository;
    private final RequestMovieService requestMovieService;
    private final WatchlistMovieRepository watchlistMovieRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void addFavoriteMovie(Long userId, Long movieId) {
        requestMovieService.requestFavoriteMovieById(userId, movieId);
    }

    public List<FavoriteMovie> findFavoriteMovies(long userId) {
        return favoriteMovieRepository.findAllByUserId(userId);
    }

    @Transactional
    public void deleteFavoriteMovie(Long userId, Long movieId) {
        favoriteMovieRepository.deleteByUserIdAndMovieId(userId, movieId);
    }

    public void addMovieToWatchlist(long userId, long movieId) {
        requestMovieService.requestWatchlistMovieById(userId, movieId);
    }

    public List<WatchlistMovie> findWatchlistMovies(long userId) {
        return watchlistMovieRepository.findAllByUserId(userId);
    }

    @Transactional
    public void deleteMovieFromWatchlist(long userId, long movieId) {
        watchlistMovieRepository.deleteByUserIdAndMovieId(userId, movieId);
    }
}
