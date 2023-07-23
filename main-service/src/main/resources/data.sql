merge into roles (id, name)
values
    (1 , 'ROLE_USER');

merge into users (id, username, password, email)
values
    (1, 'user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com');

merge into users_roles (user_id, role_id)
values
    (1, 1);