package com.boredgameswap.boardgames.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "games")
public class Game {
    public enum Genre {
        FAMILY,
        DEXTERITY,
        TRIVIA,
        PARTY,
        STRATEGY,
        CREATIVE,
        COOPERATIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Genre genre;

    @Max(2020)
    @Min(0)
    private int yearReleased;

    @NotNull
    @Min(1)
    private int numberOfPlayers;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "game",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;

    protected Game() {}

    public Game(@NotBlank String name,
                @NotBlank String description,
                @NotBlank Genre genre,
                @Max(2020) @Min(0) int yearReleased,
                @NotBlank int numberOfPlayers,
                User user) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.yearReleased = yearReleased;
        this.user = user;
        this.numberOfPlayers = numberOfPlayers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
