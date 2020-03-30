package com.boredgameswap.boardgames.repository;

import com.boredgameswap.boardgames.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, UUID> { }
