CREATE TABLE IF NOT EXISTS users (
    id                    bigint,
    username              varchar(30) not null unique,
    password              varchar(80) not null,
    email                 varchar(50) unique,
    primary key (id)
    );

CREATE TABLE IF NOT EXISTS roles (
    id                    int,
    name                  varchar(50) not null,
    primary key (id)
    );

CREATE TABLE IF NOT EXISTS users_roles (
    user_id               bigint not null,
    role_id               int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
    );