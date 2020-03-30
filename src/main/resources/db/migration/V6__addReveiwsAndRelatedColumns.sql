CREATE TABLE reviews(
    id UUID NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    "description" VARCHAR(500) NOT NULL,
    user_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    game_id UUID NOT NULL,
    FOREIGN KEY (game_id) REFERENCES games (id)
);