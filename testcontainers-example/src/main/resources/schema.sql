-- Starts 4. because 3 new row are inserted in data.sql file
CREATE SEQUENCE IF NOT EXISTS PRODUCT_SEQ START 4;

CREATE TABLE IF NOT EXISTS PRODUCT(
  ID SERIAL PRIMARY KEY,
  NAME VARCHAR(255) NOT NULL
);

