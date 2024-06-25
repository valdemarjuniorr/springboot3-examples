CREATE TABLE IF NOT EXISTS orders (
    id serial primary key,
    product_id integer not null,
    quantity integer not null
);

CREATE TABLE IF NOT EXISTS orders_confirmation (
    id serial primary key,
    order_id integer not null,
    foreign key (order_id) references orders (id)
);

