CREATE TABLE IF NOT EXISTS DEPARTMENTS (
    id serial NOT NULL,
    name VARCHAR NOT NULL,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS PRODUCTS (
    id serial NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    department_id INT NOT NULL,

    primary key (id),
    foreign key (department_id) references departments (id)
);

