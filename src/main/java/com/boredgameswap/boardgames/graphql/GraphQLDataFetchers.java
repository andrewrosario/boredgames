package com.boredgameswap.boardgames.graphql;

import com.boredgameswap.boardgames.service.UserService;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

@Component
public class GraphQLDataFetchers {
    private UserService userService;

    public GraphQLDataFetchers(UserService userService) {
        this.userService = userService;
    }

    public DataFetcher getUsersDataFetcher() {
        return dataFetchingEnvironment -> {
            return userService.getAllUsers();
        };
    }

    public DataFetcher getUserByEmail() {
        return dataFetchingEnvironment -> {
            String email = dataFetchingEnvironment.getArgument("email");
            return userService.getUserByEmail(email);
        };
    }
}
