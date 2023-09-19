create table if not exists coffee_maker {
  review_date date not null,
  handle text not null,
  rating float not null,
  helpfulness_rating float not null,
  review text not null
}
