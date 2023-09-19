DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS users_id_seq;

CREATE SEQUENCE users_id_seq START WITH 1;
CREATE TABLE users
(
  id       INTEGER        NOT NULL DEFAULT nextval('users_id_seq') PRIMARY KEY,
  name     VARCHAR        NOT NULL,
  password VARCHAR        NOT NULL,
  email    VARCHAR UNIQUE NOT NULL
);
