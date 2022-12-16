--liquibase formatted sql

--changeset musayev:1
-- auto-generated definition
CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(255) UNIQUE NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    birth_date DATE                NOT NULL,
    firstname  VARCHAR(255)        NOT NULL,
    lastname   VARCHAR(255)        NOT NULL,
    role       VARCHAR(255)        NOT NULL
);

--rollback DROP TABLE users

--changeset musayev:2

CREATE TABLE bucket
(
    id      SERIAL PRIMARY KEY,
--     status  VARCHAR(32),
    user_id INT REFERENCES users
);

--rollback DROP TABLE bucket

--changeset musayev:3

create table product
(
    id    SERIAL PRIMARY KEY,
    price NUMERIC(19, 2),
    title VARCHAR(255)
);

--rollback DROP TABLE product

--changeset musayev:4

CREATE TABLE buckets_products
(
    id         SERIAL PRIMARY KEY,
    bucket_id  INT REFERENCES bucket ON DELETE CASCADE,
    product_id INT REFERENCES product ON DELETE CASCADE
);

--rollback DROP TABLE buckets_products

--changeset musayev:5

CREATE TABLE category
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

--rollback DROP TABLE category

--changeset musayev:6

CREATE TABLE products_categories
(
    id          SERIAL PRIMARY KEY,
    category_id INT REFERENCES category,
    product_id  INT REFERENCES product
);

--rollback DROP TABLE products_categories

--changeset musayev:7

CREATE TABLE orders
(
    id         SERIAL PRIMARY KEY,
    user_id    INT REFERENCES users,
    address    VARCHAR(255),
    status     VARCHAR(32),
    sum        NUMERIC(19, 2),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

--rollback DROP TABLE orders

--changeset musayev:8

create table orders_details
(
    id         SERIAL PRIMARY KEY,
    amount     NUMERIC(19, 2),
    price      NUMERIC(19, 2),
    order_id   INT REFERENCES orders,
    product_id INT REFERENCES product
);

--rollback DROP TABLE orders_details