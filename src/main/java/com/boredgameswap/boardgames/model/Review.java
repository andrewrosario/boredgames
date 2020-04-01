package com.boredgameswap.boardgames.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private int rating;

    public Review() { }

    public Review(@JsonProperty("user")User user,
                  @JsonProperty("game")Game game,
                  @JsonProperty("title") @NotBlank String title,
                  @JsonProperty("description") @NotBlank String description,
                  @JsonProperty("rating") @NotBlank int rating) {
        this.user = user;
        this.game = game;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public Game getGame() {
        return game;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
