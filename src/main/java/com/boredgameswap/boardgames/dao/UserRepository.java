package com.boredgameswap.boardgames.dao;

import com.boredgameswap.boardgames.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    List<User> findByLastName(String lastName);

    Optional<User> findById(UUID id);
}
