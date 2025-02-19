DROP TABLE IF EXISTS AUTH_USER_ROLES;
DROP TABLE IF EXISTS AUTH_ROLE;
DROP TABLE IF EXISTS AUTH_USER;

CREATE TABLE auth_role
(
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (name)
);

CREATE TABLE `auth_user`
(
    user_id  BIGINT generated by default as identity,
    username VARCHAR(255),
    password VARCHAR(255),
    enabled  BOOLEAN,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT uc_user_username UNIQUE (username)
);

CREATE TABLE `AUTH_USER_ROLES`
(
    user_user_id BIGINT generated by default as identity,
    roles_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (user_user_id, roles_name),
    CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_name) REFERENCES auth_role (name),
    CONSTRAINT fk_userol_on_user FOREIGN KEY (user_user_id) REFERENCES `auth_user` (user_id)
);
