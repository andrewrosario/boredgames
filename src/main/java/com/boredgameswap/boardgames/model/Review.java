package com.boredgameswap.boardgames.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private String review;

    @NotBlank
    private int rating;

    public Review(User user,
                  Game game,
                  @JsonProperty("title") @NotBlank String title,
                  @JsonProperty("review") @NotBlank String review,
                  @JsonProperty("rating") @NotBlank int rating) {
        this.user = user;
        this.game = game;
        this.title = title;
        this.review = review;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
