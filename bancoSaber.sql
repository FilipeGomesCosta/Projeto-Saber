CREATE DATABASE IF NOT EXISTS bancoSaber;
USE bancoSaber;

CREATE TABLE Usuario (
    email VARCHAR(100) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    senha VARCHAR(60) NOT NULL
);

CREATE TABLE Mochila (
    email VARCHAR(100) NOT NULL PRIMARY KEY,
    quantidadePapiro INT DEFAULT 0,
    quantidadeLampada INT DEFAULT 0,
    FOREIGN KEY (email) REFERENCES Usuario(email)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Progresso (
    email VARCHAR(100) NOT NULL PRIMARY KEY,
    missao INT DEFAULT 1,
    xp INT DEFAULT 0,
    moedas INT DEFAULT 0,
    nivel INT DEFAULT 1,
    FOREIGN KEY (email) REFERENCES Usuario(email)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

