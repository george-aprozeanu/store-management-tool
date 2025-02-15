insert into AUTH_ROLE (NAME)
values ('Consumer'),
       ('Administrator');

insert into AUTH_USER (USERNAME, PASSWORD, ENABLED)
VALUES ('user', '$2a$10$X5wFBtLrL/kHcmrOGGTrGufsBX8CJ0WpQpF3pgeuxBB/H73BK1DW6', true);

INSERT into AUTH_USER_ROLES (user_user_id, roles_name)
VALUES ((select user_id from auth_user where username = 'user'), 'Consumer'),
       ((select user_id from auth_user where username = 'user'), 'Administrator');
