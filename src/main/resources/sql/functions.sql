/*
    storehouse/stoke
 */
create or replace function add_stoke(_location character varying (500), _max_count integer)
    returns boolean as
$$
begin
    if exists(select * from "storehouse" where location = _location) then
        insert into "storehouse"("location", max_count)
        values(_location, _max_count);
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_stoke(_id integer, _location character varying (500), _max_count integer)
    returns boolean as
$$
begin
    if exists(select * from "storehouse" where id_storehouse = _id) then
        update "section" set "location" = _location, "max_count" = _max_count where id_storehouse = _id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_stoke(_id integer)
    returns boolean as
$$
declare
	_count integer;
begin
	select count(*) from "section" where id_storehouse = _id into _count;
    if (exists(select * from "storehouse" where id_storehouse = _id) and _count = 0) then
        delete from "storehouse" where id_storehouse = _id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

/*
    section
*/

create or replace function add_section(_id_store integer, _max_amount integer, _number integer)
    returns boolean as
$$
declare
	_count integer;
	_max integer;
	_id integer;
begin
	select COUNT(*) from "section" where id_storehouse = _id_store into _count;
	select max_count from "storehouse" where id_storehouse = _id_store into _max;
    if (_count < _max) then
        insert into "section"(id_storehouse, amount, max_amount, "number")
        values(_id_store, 0, _max_amount, _number);
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_section(_id integer, _max_amount integer, _number integer)
    returns boolean as
$$
begin
     if exists(select * from "section" where id_section = _id) then
        update "section" set "max_amount" = _max_amount, "number" = _number where id_section = _id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function delete_section(_id integer, _max_amount integer)
    returns boolean as
$$
begin
    if not exists(select * from "spare_part" where id_section = _id) then
        delete from "section" where id_section = _id;
    else return false;
    end if;
end;
$$
language plpgsql;

/*
    type
*/

create or replace function add_type(_name character varying (255))
    returns boolean as
$$
begin
    if not exists(select * from "type" where "name" = _name) then
        insert into "type"("name") values (_name);
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_type(_id integer, _name character varying (255))
    returns boolean as
$$
begin
    if not exists(select * from "type" where "name" = _name) then
        update "type" set "name" = _name where id_type = _id;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function dell_type(_id integer)
    returns boolean as
$$
begin
    if not exists(select * from "spare_type" where id_type = _id) then
        delete from "type" where id_type = _id;
    else return false;
    end if;
end;
$$
language plpgsql;

/*
    spare_part
*/

create or replace function add_spare_part(_id_type integer, _id_section integer, _name varchar(500), _price real, _proportions varchar(500))
    returns boolean as
$$
begin
    if not exists(select * from "spare_part" where "name" = _name) then
        insert into "spare_part"(id_type, id_section, "name", price, proportions)
        values(_id_type, _id_section, _name, _price, _proportions);
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_spare_part(_id integer, _id_type integer, _id_section integer, _name varchar(500), _price real, _proportions varchar(500))
    returns boolean as
$$
begin
    if exists(select * from "spare_part" where id_spare_part = _id) then
		update "spare_part" set id_type = _id_type, id_section = _id_section,
		"name" = _name, price = _price, proportions = _proportions where id_spare_part = _id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_spare_part(_id integer)
    returns boolean as
$$
begin
    if not exists(select * from "position" where id_spare_part = _id) then
		delete from "spare_part" where id_spare_part = _id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;