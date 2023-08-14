set search_path to emarket;

create table if not exists cdc_heartbeat
(
    id           bigserial primary key,

    connector    varchar(256) not null unique,

    created_date timestamp    not null default current_timestamp

);