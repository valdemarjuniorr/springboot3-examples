CREATE TABLE IF NOT EXISTS product
(
    id    serial PRIMARY KEY,
    name  VARCHAR(255)   NOT NULL,
    price DECIMAL(10, 2) NOT NULL
)