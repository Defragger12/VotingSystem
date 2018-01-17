DROP TABLE user_roles
IF EXISTS;
DROP TABLE users
IF EXISTS;
DROP TABLE dishes
IF EXISTS;
DROP TABLE restaurant
IF EXISTS;
DROP TABLE results
IF EXISTS;
DROP SEQUENCE global_seq
IF EXISTS;
CREATE SEQUENCE GLOBAL_SEQ
  AS INTEGER
  START WITH 100000;

CREATE TABLE restaurant
(
  id   INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE dishes
(
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name          VARCHAR(255) NOT NULL,
  price         INTEGER      NOT NULL,
  restaurant_id INTEGER      NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
    ON DELETE CASCADE
);

CREATE TABLE users
(
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name          VARCHAR(255) NOT NULL,
  password      VARCHAR(255) NOT NULL,
  restaurant_id INTEGER DEFAULT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
    ON DELETE SET NULL
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE
);

