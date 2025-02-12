drop table if exists `auth_role` cascade;
drop table if exists `auth_user` cascade;
drop table if exists `auth_user_auth_roles` cascade;
CREATE TABLE role
(
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (name)
);

CREATE TABLE `user`
(
    user_id  BIGINT NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    enabled  BOOLEAN,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

CREATE TABLE `user_roles`
(
    auth_user_user_id BIGINT       NOT NULL,
    roles_name   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (user_user_id, roles_name)
);

ALTER TABLE `user`
    ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE `user_roles`
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_name) REFERENCES `auth_role` (name);

ALTER TABLE `user_roles`
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_user_id) REFERENCES  `auth_user` (user_id);
