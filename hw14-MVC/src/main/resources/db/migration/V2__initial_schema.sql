
create table adress
(
    id   bigint not null primary key,
    street varchar(50),
    client_id bigint not null references client (id)
);

