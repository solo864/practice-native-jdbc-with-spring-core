--liquibase formatted sql

--changeset musayev:1
ALTER TABLE product
    ADD COLUMN image VARCHAR(64);