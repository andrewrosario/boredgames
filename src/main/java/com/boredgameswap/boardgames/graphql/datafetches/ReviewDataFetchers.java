package com.boredgameswap.boardgames.graphql.datafetches;

import com.boredgameswap.boardgames.service.ReviewService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReviewDataFetchers {

    @Autowired
    ReviewService reviewService;

    public DataFetcher getAllReviews() {
        return dataFetchingEnvironment -> reviewService.getAllReviews();
    }

    public DataFetcher createReview() {
        return dataFetchingEnvironment -> {
            Map<String, Object> reviewInput = dataFetchingEnvironment.getArgument("reviewInput");
            String userId = dataFetchingEnvironment.getArgument("userId");
            String gameId = dataFetchingEnvironment.getArgument("gameId");
            return reviewService.create(reviewInput, userId, gameId);
        };
    }
}
