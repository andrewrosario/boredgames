package com.boredgameswap.boardgames.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.boredgameswap.boardgames.graphql.payloads.LoginPayload;
import com.boredgameswap.boardgames.model.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthService {
    private UserService userService;
    private BCryptPasswordEncoder encoder;

    public AuthService(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    public LoginPayload login(String email, String password) throws AuthenticationException{
        User user = userService.getUserByEmail(email);
        if(encoder.matches(password, user.getPassword())) {
             String jwt = JWT.create()
                    .withExpiresAt(new Date(System.currentTimeMillis() + 864_000_000))
                    .sign(Algorithm.HMAC512("SUPERSECRETSTRING".getBytes()));
            return new LoginPayload(jwt, user);
        }
        throw new RuntimeException("Bad Password");
    }

    public Boolean verify(String jwt) {
        try {
            JWT.require(Algorithm.HMAC512("SUPERSECRETSTRING".getBytes()))
                    .build()
                    .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
