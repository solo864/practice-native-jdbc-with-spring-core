--liquibase formatted sql

--changeset musayev:1
-- auto-generated definition

INSERT INTO users(id, username, firstname, lastname, email, password, birth_date, role)
VALUES (4, 'admin', 'admin', 'admin', 'admin', '{noop}admin', '2002-02-06', 'ADMIN'),
       (5, 'user', 'user', 'user', 'user', '{noop}user', '2002-02-06', 'USER'),
       (6, 'client', 'client', 'client', 'client', '{noop}client', '2002-02-06', 'CLIENT')