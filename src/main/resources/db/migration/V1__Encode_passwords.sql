create extension if not extension pgcrypto;

update usr set password = crypt(password, gen_salt('bf', 8));