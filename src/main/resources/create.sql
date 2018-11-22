drop database if exists demo;

create database demo;
use demo;

create table place (
  id      int         not null  primary key   unique  auto_increment,
  name    varchar(50) not null,
  address varchar(50),
  info    text,
  removed bit         not null
);


create table camp_type (
  id   int         not null  primary key   unique  auto_increment,
  name varchar(50) not null
);

create table camp (
  id             int         not null  primary key   unique  auto_increment,
  name           varchar(50) not null,
  age_min        int         not null,
  age_max        int         not null,
  date_start     date        not null,
  date_finish    date        not null,
  children_count int         not null,
  price          int         not null,
  removed        bit         not null,
  icon           blob,
  info           text,
  type_id        int
    references camp_type (id)
      on update cascade
      on delete set null,
  place_id       int
    references place (id)
      on update cascade
);

create table camp_photo (
  id      int          not null  primary key   unique  auto_increment,
  image   varchar(100) not null,
  removed bit          not null,
  camp_id int
    references camp (id)
      on update cascade
      on delete cascade
);


create table user_status (
  id   int not null  primary key   unique  auto_increment,
  name varchar(20)
);

create table user (
  id        int         not null  primary key   unique  auto_increment,
  name      varchar(50),
  login     varchar(20) not null                unique,
  password  varchar(100),
  removed   bit         not null,
  status_id int         not null
    references user_status (id)
      on update cascade
);


create table order_status (
  id   int not null  primary key   unique  auto_increment,
  name varchar(20)
);

create table buy_order (
  id        int not null   primary key  unique  auto_increment,
  removed   bit not null,
  user_id   int not null
    references user_status (id)
      on update cascade,
  status_id int not null
    references order_status (id)
      on update cascade
);


create table order_camp_id (
  id       int not null  primary key  unique   auto_increment,
  count    int not null,
  removed  bit not null,
  order_id int
    references buy_order (id)
      on update cascade
      on delete cascade,
  camp_id  int
    references camp (id)
      on update cascade
);

commit;

