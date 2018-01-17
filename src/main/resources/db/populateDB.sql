DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM restaurant;
ALTER SEQUENCE GLOBAL_SEQ
RESTART WITH 100000;

INSERT INTO restaurant (name) VALUES
  ('оригинальный'),
  ('странный');

INSERT INTO users (name, password, restaurant_id) VALUES
  ('User', 'user123', NULL),
  ('Admin', 'admin123', NULL);


INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100002),
  ('ROLE_ADMIN', 100003);

INSERT INTO dishes (name, restaurant_id, price) VALUES
  ('рыба', 100000, 500),
  ('овощи', 100000, 300),
  ('мучное', 100001, 400),
  ('суп', 100001, 300)