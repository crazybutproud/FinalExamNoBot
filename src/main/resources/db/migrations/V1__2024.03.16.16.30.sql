create table countries_info (
                                id  serial not null,
                                best_time varchar(255),
                                capital varchar(255),
                                country varchar(255),
                                mountain boolean,
                                sea boolean,
                                tem_sea int,
                                tem int,
--                                 profile_id int,
                                primary key (id)
);
create table feedback (
                          id  serial not null,
                          data varchar(255),
                          name varchar(255),
                          number varchar(255),
                          primary key (id)
);
create table profiles (
                          id  serial not null,
                          email varchar(255),
                          name varchar(255),
                          surname varchar(255),
--                           user_id int,
                          primary key (id)
);
create table results (
                         code int not null,
                         country_id int,
                         profile_id int,
                         primary key (code)
);
create table users (
                       id  serial not null,
                       password varchar(255),
                       username varchar(255),
                       primary key (id)
);

