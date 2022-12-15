INSERT INTO users (id, username, firstname, lastname, email, password, birth_date, role, image)
VALUES (1, 'Isobelle1337', 'Isobelle', 'Valentine', 'isabelle@gmail.com', 'valentine123', '1999-01-01', 'USER', null),
       (2, 'Findlay1337', 'Findlay', 'Miruna', 'findlay@gmail.com', 'miruna123', '2020-02-02', 'USER', null),
       (3, '1337', 'Cleveland', 'Abida', 'cleveland@gmail.com', '{noop}1337', '2001-03-03', 'USER', null);

ALTER SEQUENCE users_id_seq RESTART WITH 4;

INSERT INTO bucket (id, user_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

ALTER SEQUENCE bucket_id_seq RESTART WITH 4;


INSERT INTO product (id, price, title)
VALUES (1, 11, 'test'),
       (2, 2, 'test2'),
       (3, 3, 'test3'),
       (4, 5, 'test4'),
       (5, 1, 'test5'),
       (6, 10, 'test6'),
       (7, 20, 'test7'),
       (8, 25, 'test8'),
       (9, 34, 'test9'),
       (10, 45, 'test10'),
       (11, 55, 'test11');

ALTER SEQUENCE product_id_seq RESTART WITH 12;


INSERT INTO buckets_products(id, bucket_id, product_id)
VALUES (1, 1, 5),
       (2, 1, 6),
       (3, 2, 7),
       (4, 2, 2),
       (5, 2, 3),
       (6, 2, 4),
       (7, 3, 7),
       (8, 3, 2),
       (9, 3, 11),
       (10, 3, 10);

ALTER SEQUENCE buckets_products_id_seq RESTART WITH 11;


INSERT INTO category (id, name)
VALUES (1, 'Meat'),
       (2, 'Fish'),
       (3, 'Cheese'),
       (4, 'fruits');

ALTER SEQUENCE category_id_seq RESTART WITH 5;;

INSERT INTO products_categories (id, category_id, product_id)
VALUES (1, 1, 11),
       (2, 1, 10),
       (3, 1, 4),
       (4, 2, 9),
       (5, 2, 9),
       (6, 3, 8),
       (7, 3, 7),
       (8, 4, 6),
       (9, 4, 5),
       (10, 1, 4),
       (11, 1, 3),
       (12, 3, 2),
       (13, 3, 1);

ALTER SEQUENCE products_categories_id_seq RESTART WITH 14;


INSERT INTO orders (id, user_id, address, status, sum, created_at, updated_at)
VALUES (1, 1, 'test123', 'NEW', 10, '2020-05-07', null),
       (2, 2, 'test456', 'CANCEL', 10, '2020-06-07', null),
       (3, 3, 'test678', 'RETURNED', 5, '2020-07-08', null);

ALTER SEQUENCE orders_id_seq RESTART WITH 4;

INSERT INTO orders_details (id, amount, price, order_id, product_id)
VALUES (1, 12, 111, 1, 11),
       (2, 15, 59, 1, 4),
       (3, 14, 59.99, 1, 5),
       (4, 22, 100, 2, 6),
       (5, 18, 50, 2, 2),
       (6, 10, 30, 2, 10),
       (7, 4, 14, 3, 6),
       (8, 6, 56, 3, 8),
       (9, 9, 32, 3, 3);

ALTER SEQUENCE orders_details_id_seq RESTART WITH 10;