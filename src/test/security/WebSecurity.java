package com.boredgameswap.boardgames.security;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsService  userDetailsService;
    private BCryptPasswordEncoder encoder;

    public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }
}
