merge into roles (id, name)
values
    (1 , 'ROLE_USER'),
    (2 , 'ROLE_ADMIN');

merge into users (id, username, password, email)
values
    (1, 'user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
    (50, 'admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

merge into users_roles (user_id, role_id)
values
    (1, 1),
    (50, 2);