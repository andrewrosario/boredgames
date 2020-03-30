ALTER TABLE games
ADD number_of_players INTEGER CHECK (number_of_players > 0);