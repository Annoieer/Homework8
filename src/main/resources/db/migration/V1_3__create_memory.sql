create table if not exists memory (
id bigserial primary key,
amount bigserial,
user_id bigserial,
transaction_date datetime,
is_cancelled boolean
);