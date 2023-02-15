-- liquibase formatted sql

-- changeset ymalykh:1

CREATE TABLE IF NOT EXISTS users
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username      VARCHAR(150) UNIQUE NOT NULL,
    email         VARCHAR(50)         NOT NULL,
    password      VARCHAR(255)        NOT NULL,
    creation_date DATE                NOT NULL
);

CREATE TABLE IF NOT EXISTS voting
(
    id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pros BIGINT DEFAULT 0,
    cons BIGINT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS vote
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pros          BOOLEAN,
    cons          BOOLEAN,
    creation_date DATE,
    voting_id     BIGINT REFERENCES voting (id),
    user_id       BIGINT REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS quote
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    content       TEXT NOT NULL,
    creation_date DATE NOT NULL,
    update_date   DATE,
    user_id       BIGINT REFERENCES users (id),
    voting_id     BIGINT REFERENCES voting (id)
);

INSERT INTO users(username, email, password, creation_date)
VALUES ('user', 'test@test.ru', '{bcrypt}$2a$10$FrBQOvCSsYATs5J5ZT8RFuo6E2.QvPybK5c.5INOaIVjdUt/5Tyb2', '2023-02-13');
-- password = 'password'


