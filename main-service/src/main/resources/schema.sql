CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    username VARCHAR(30) UNIQUE                            NOT NULL,
    password VARCHAR(80)                                   NOT NULL,
    email VARCHAR(30) UNIQUE                               NOT NULL
    );

CREATE TABLE IF NOT EXISTS roles (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(50)                                        NOT NULL
    );

CREATE TABLE IF NOT EXISTS users_roles (
    user_id               bigint not null,
    role_id               int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
    );
