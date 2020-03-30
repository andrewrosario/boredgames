CREATE TABLE games(
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR NOT NULL,
    year_released INTEGER CHECK (year_released < 2021) CHECK (year_released > 0),
    genre VARCHAR,
    user_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
)