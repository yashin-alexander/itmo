# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table points (
  id                            serial not null,
  x                             float,
  y                             float,
  r                             float,
  is_inside                     boolean,
  color                         varchar(255),
  owner                         varchar(255),
  constraint pk_points primary key (id)
);

create table users (
  id                            serial not null,
  name                          varchar(255),
  password                      varchar(255),
  constraint pk_users primary key (id)
);


# --- !Downs

drop table if exists points cascade;

drop table if exists users cascade;

