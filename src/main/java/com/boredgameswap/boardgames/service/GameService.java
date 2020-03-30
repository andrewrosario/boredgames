package com.boredgameswap.boardgames.service;

import com.boredgameswap.boardgames.model.Game;
import com.boredgameswap.boardgames.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void create(Game game) {
        gameRepository.save(game);
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
