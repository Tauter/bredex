CREATE TABLE IF NOT EXISTS user_account
(
    id        int auto_increment,
    user_name varchar(50)  not null,
    email     varchar(255) not null unique,
    password  varchar(255) not null,
    PRIMARY KEY (id)
);
