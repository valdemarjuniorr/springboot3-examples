create sequence if not exists product_seq;

create table if not exists product (
    id integer primary key default nextval('product_seq'),
    description varchar(255) not null
);
