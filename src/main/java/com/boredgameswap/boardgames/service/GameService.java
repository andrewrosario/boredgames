package com.boredgameswap.boardgames.service;

import com.boredgameswap.boardgames.model.Game;
import com.boredgameswap.boardgames.model.User;
import com.boredgameswap.boardgames.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserService userService;

    @Autowired
    public GameService(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    public Game create(Map<String, Object> gameInput, String email) {
        ObjectMapper mapper = new ObjectMapper();
        Game game = mapper.convertValue(gameInput, Game.class);
        User user = userService.getUserByEmail(email);
        game.setUser(user);
        return gameRepository.save(game);
    }

    public Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(UUID id) {
        return gameRepository.findById(id);
    }

    public void deleteGame(UUID id) {
        Optional<Game> game = gameRepository.findById(id);
        game.ifPresent(gameRepository::delete);
    }
}
