CREATE TABLE IF NOT EXISTS game (
    id_game INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    editora VARCHAR(255),
    genero VARCHAR(100),
    ano_lancamento INT
);

CREATE TABLE IF NOT EXISTS player (
    id_player INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT,
    fav_game INT,
    CONSTRAINT fk_player_game FOREIGN KEY (fav_game) REFERENCES game(id_game)
);
