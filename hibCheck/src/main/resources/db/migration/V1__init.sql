create table users
(
    id        serial primary key,
    name      varchar(255) not null,
    surname   varchar(255) not null,
    birthdate date         not null
);

create unique index users_name_surname_birthdate
    on users (name, surname, birthdate);

create table user_accounts
(
    account_id serial primary key,
    user_id    int not null,
    balance    decimal,
    currency   varchar(255),
    unique (user_id, currency),
    foreign key (user_id) references users (id)
);