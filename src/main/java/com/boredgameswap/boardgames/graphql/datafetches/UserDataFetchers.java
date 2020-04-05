package com.boredgameswap.boardgames.graphql.datafetches;

import com.boredgameswap.boardgames.service.AuthService;
import com.boredgameswap.boardgames.service.UserService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserDataFetchers {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    public DataFetcher getUsersDataFetcher() {
        return dataFetchingEnvironment -> {
            return userService.getAllUsers();
        };
    }

    public DataFetcher getUserByEmailFetcher() {
        return dataFetchingEnvironment -> {
            String email = dataFetchingEnvironment.getArgument("email");
            return userService.getUserByEmail(email);
        };
    }

    public DataFetcher createUserFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, Object> userInput = dataFetchingEnvironment.getArgument("userInput");
            return userService.create(userInput);
        };
    }

    public DataFetcher login() {
        return dataFetchingEnvironment -> {
            String email = dataFetchingEnvironment.getArgument("email");
            String password = dataFetchingEnvironment.getArgument("password");
            return authService.login(email, password);
        };

    }
}


