--liquibase formatted sql

--changeset musayev:1
ALTER TABLE users
    ADD COLUMN image VARCHAR(64);