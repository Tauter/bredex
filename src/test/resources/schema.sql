CREATE TABLE IF NOT EXISTS user_account
(
    id        int auto_increment,
    user_name varchar(50)  not null,
    email     varchar(255) not null unique,
    password  varchar(255) not null,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ad
(
    id              int auto_increment,
    user_account_id int          not null,
    brand           varchar(20)  not null,
    type            varchar(20)  not null,
    description     varchar(200) not null,
    price           int          not null,
    PRIMARY KEY (id),
    FOREIGN KEY (user_account_id) REFERENCES user_account (id)
)
