package com.boredgameswap.boardgames.graphql;

import com.boredgameswap.boardgames.graphql.datafetches.GameDataFetchers;
import com.boredgameswap.boardgames.graphql.datafetches.ReviewDataFetchers;
import com.boredgameswap.boardgames.graphql.datafetches.UserDataFetchers;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

@Component
public class GraphQLProvider {

    private GraphQL graphQL;

    @Autowired
    UserDataFetchers userDataFetchers;
    @Autowired
    GameDataFetchers gameDataFetchers;
    @Autowired
    ReviewDataFetchers reviewDataFetchers;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query")
                        .dataFetcher("users", userDataFetchers.getUsersDataFetcher())
                        .dataFetcher("userByEmail", userDataFetchers.getUserByEmailFetcher())
                        .dataFetcher("games", gameDataFetchers.getAllGames())
                        .dataFetcher("reviews", reviewDataFetchers.getAllReviews()))
                .type(TypeRuntimeWiring.newTypeWiring("Mutation")
                    .dataFetcher("createUser", userDataFetchers.createUserFetcher())
                    .dataFetcher("createGame", gameDataFetchers.createGameFetcher())
                .dataFetcher("createReview", reviewDataFetchers.createReview())
                .dataFetcher("login", userDataFetchers.login()))
                .build();
    }
}