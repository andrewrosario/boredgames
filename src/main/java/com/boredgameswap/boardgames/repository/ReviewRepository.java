package com.boredgameswap.boardgames.repository;

import com.boredgameswap.boardgames.model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, UUID> {
}
