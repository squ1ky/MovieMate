package com.squiky.userservice.controller;

import com.squiky.userservice.model.FavoriteMovie;
import com.squiky.userservice.model.User;
import com.squiky.userservice.model.WatchlistMovie;
import com.squiky.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId) {
        return userService.findById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable long userId, @RequestBody User user) {
        User userToUpdate = userService.findById(userId);
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setUsername(user.getUsername());

        return userService.save(userToUpdate);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable long userId) {
        userService.delete(userId);
    }

    @PostMapping("/{userId}/favorites/{movieId}")
    public void addFavoriteMovie(@PathVariable long userId, @PathVariable long movieId) {
        userService.addFavoriteMovie(userId, movieId);
    }

    @GetMapping("/{userId}/favorites")
    public List<FavoriteMovie> findFavoriteMovies(@PathVariable long userId) {
        return userService.findFavoriteMovies(userId);
    }

    @DeleteMapping("/{userId}/favorites/{movieId}")
    public void deleteFavoriteMovie(@PathVariable long userId, @PathVariable long movieId) {
        userService.deleteFavoriteMovie(userId, movieId);
    }

    @PostMapping("/{userId}/watchlist/{movieId}")
    public void addMovieToWatchlist(@PathVariable long userId, @PathVariable long movieId) {
        userService.addMovieToWatchlist(userId, movieId);
    }

    @GetMapping("/{userId}/watchlist")
    public List<WatchlistMovie> findWatchlist(@PathVariable long userId) {
        return userService.findWatchlistMovies(userId);
    }

    @DeleteMapping("/{userId}/watchlist/{movieId}")
    public void deleteMovieFromWatchlist(@PathVariable long userId, @PathVariable long movieId) {
        userService.deleteMovieFromWatchlist(userId, movieId);
    }
}
