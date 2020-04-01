package com.boredgameswap.boardgames.service;

import com.boredgameswap.boardgames.model.Game;
import com.boredgameswap.boardgames.model.Review;
import com.boredgameswap.boardgames.model.User;
import com.boredgameswap.boardgames.repository.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {
    @Autowired
    UserService userService;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    GameService gameService;

    public Review create(Map<String, Object> reviewInput, String userId, String gameId) {
        ObjectMapper mapper = new ObjectMapper();
        Review review = mapper.convertValue(reviewInput, Review.class);
        Optional<Game> game = gameService.getGameById(UUID.fromString(gameId));
        Optional<User> user = userService.getUserById(UUID.fromString(userId));
        review.setUser(user.orElseThrow());
        review.setGame(game.orElseThrow());
        return reviewRepository.save(review);
    }

    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
