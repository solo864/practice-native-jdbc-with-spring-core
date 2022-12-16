--liquibase formatted sql

--changeset musayev:1
-- auto-generated definition

ALTER TABLE users
    ADD password VARCHAR(128) DEFAULT '{noop}123';