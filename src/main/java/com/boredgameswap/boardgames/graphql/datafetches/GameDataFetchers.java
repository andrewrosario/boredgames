package com.boredgameswap.boardgames.graphql.datafetches;

import com.boredgameswap.boardgames.service.GameService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GameDataFetchers {

    @Autowired
    private GameService gameService;

    public DataFetcher createGameFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, Object> gameInput = dataFetchingEnvironment.getArgument("game");
            String userEmail = dataFetchingEnvironment.getArgument("userEmail");
            return gameService.create(gameInput, userEmail);
        };
    }

    public DataFetcher getAllGames() {
        return dataFetchingEnvironment -> gameService.getAllGames();
    }
}
