package com.boredgameswap.boardgames.graphql.payloads;

import com.boredgameswap.boardgames.model.User;

public class LoginPayload {
    private String jwt;
    private User user;

    public LoginPayload(String jwt, User user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public User getUser() {
        return user;
    }
}
