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
    pros BIGINT,
    cons BIGINT
);

CREATE TABLE IF NOT EXISTS vote
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pros          INT,
    cons          int,
    creation_date DATE,
    voting_id     BIGINT REFERENCES voting (id)
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


