insert into AUTH_ROLE (NAME)
values ('Consumer'),
       ('Administrator');

insert into AUTH_USER (USERNAME, PASSWORD, ENABLED)
VALUES ('user', '{bcrypt}$2a$10$X5wFBtLrL/kHcmrOGGTrGufsBX8CJ0WpQpF3pgeuxBB/H73BK1DW6')
