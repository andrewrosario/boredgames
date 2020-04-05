package com.boredgameswap.boardgames.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users",
        uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @NotNull
    private String password;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Game> games;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Review> reviews;

    protected User() {}


    public User(@JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("email") String email,
                @JsonProperty("password") String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public UUID getId() {
        return id;
    }
     public String getFullName() {
        return firstName + " " + lastName;
     }


}
