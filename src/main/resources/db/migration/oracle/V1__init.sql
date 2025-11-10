-- GAME table
CREATE TABLE game (
    id_game NUMBER PRIMARY KEY,
    titulo VARCHAR2(255 CHAR) NOT NULL,
    editora VARCHAR2(255 CHAR),
    genero VARCHAR2(100 CHAR),
    ano_lancamento NUMBER
);

CREATE SEQUENCE seq_game START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE OR REPLACE TRIGGER trg_game_pk
BEFORE INSERT ON game
FOR EACH ROW
BEGIN
    IF :new.id_game IS NULL THEN
        SELECT seq_game.NEXTVAL INTO :new.id_game FROM dual;
    END IF;
END;
/

-- PLAYER table
CREATE TABLE player (
    id_player NUMBER PRIMARY KEY,
    nome VARCHAR2(255 CHAR) NOT NULL,
    idade NUMBER,
    fav_game NUMBER,
    CONSTRAINT fk_player_game FOREIGN KEY (fav_game) REFERENCES game(id_game)
);

CREATE SEQUENCE seq_player START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE OR REPLACE TRIGGER trg_player_pk
BEFORE INSERT ON player
FOR EACH ROW
BEGIN
    IF :new.id_player IS NULL THEN
        SELECT seq_player.NEXTVAL INTO :new.id_player FROM dual;
    END IF;
END;
