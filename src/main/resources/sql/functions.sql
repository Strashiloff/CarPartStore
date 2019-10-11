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

create or replace function del_type(_id integer)
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

/*
    Category
*/

create or replace function add_category(_name character varying (255))
    returns boolean as
$$
begin
    if not exists(select * from "category" where "name" = _name) then
        insert into "category"("name") values (_name);
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_category(_id integer, _name character varying (255))
    returns boolean as
$$
begin
    if exists(select * from "category" where "name" = _name) then
        update "category" set "name" = _name where id_type = _id;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_category(_id integer)
    returns boolean as
$$
begin
    if not exists(select * from "purveyor" where id_category = _id) then
		delete from "category" where id_category = _id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

/*
    Country
*/

create or replace function add_country(_name character varying (255))
    returns boolean as
$$
begin
    if not exists(select * from "country" where "name" = _name) then
        insert into "country"("name") values (_name);
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_country(_id integer, _name character varying (255))
    returns boolean as
$$
begin
    if exists(select * from "country" where "name" = _name) then
        update "country" set "name" = _name where id_country = _id;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_country(_id integer)
    returns boolean as
$$
begin
    if not exists(select * from "purveyor" where id_country = _id) then
		delete from "country" where id_country = _id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

/*
    purveyor
*/

create or replace function add_purveyor(_id_category integer, _id_country integer,
	_warranty varchar(255), _name varchar(500), _adress varchar(255))
    returns boolean as
$$
begin
    if not exists(select * from "purveyor" where id_category =_id_category and "name" = _name) then
        insert into "purveyor"(id_category, id_country, warranty, "name", adress)
        values(_id_category, _id_country, _warranty, _name, _adress);
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_purveyor(_id integer, _id_category integer, _id_country integer,
	_warranty varchar(255), _name varchar(500), _adress varchar(255))
    returns boolean as
$$
begin
    if exists(select * from "purveyor" where id_purveyor = _id) then
        update "purveyor" set id_category = _id_category, id_country =_id_country,
		warranty = _warranty, "name" = _name, adress = _adress where id_purveyor = _id;
		return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_purveyor(_id integer)
    returns boolean as
$$
begin
    if not exists(select * from "suply" where id_purveyor = _id) then
		delete from "purveyor" where id_purveyor = _id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

/*
    supply
*/

create or replace function add_supply(_id_contract integer, _id_purveyor integer,
	_data timestamp, _tax real)
    returns boolean as
$$
begin
    if not exists(select * from "supply" where id_contract =_id_contract) then
        insert into "supply"(id_contract, id_purveyor, "data", tax)
        values(_id_contract, _id_purveyor, _data, _tax);
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_supply(_id_supply integer, _id_contract integer, _id_purveyor integer,
	_data timestamp, _tax real)
    returns boolean as
$$
begin
    if exists(select * from "supply" where id_supply =_id_supply) then
        update "supply" set id_contract = _id_contract, id_purveyor =_id_purveyor,
		"data" = _data, tax = _tax where id_supply = _id_supply;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_supply(_id_supply integer)
    returns boolean as
$$
begin
    if exists(select * from "supply" where id_supply =_id_supply) then
        delete from "supply" where id_supply =_id_supply;
    else return false;
    end if;
end;
$$
language plpgsql;

/* Contract */

create or replace function add_contract(_member_one varchar(255), _member_two varchar(255),
	_body text, _date timestamp)
    returns boolean as
$$
begin
    if not exists(select * from "contract" where id_contract =_id_contract) then
        insert into "contract"(member_one, member_two, body, "date")
        values(_member_one, _member_two, _body, _date);
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_contract(id_contract integer, _member_one varchar(255), _member_two varchar(255),
	_body text, _date timestamp)
    returns boolean as
$$
begin
    if not exists(select * from "contract" where id_contract =_id_contract) then
        update "contract" set member_one = _member_one, member_two =_member_two,
		body = _body, "date" = _date where id_supply = _id_supply;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_contract(_id_contract integer)
    returns boolean as
$$
begin
    if not exists(select * from "supply" where id_contract =_id_contract) then
		delete from "contract" where id_contract =_id_contract;
		return true;
    else return false;
    end if;
end;
$$
language plpgsql;

/* Position */

create or replace function add_position(_id_supply integer, _id_spare_part integer,
	_amount integer, _price real)
    returns boolean as
$$
begin
    if not exists(select * from "position" where id_spare_part =_id_spare_part) then
        insert into "position"(id_supply, id_spare_part, amount, price)
        values(_id_supply, _id_spare_part, _amount, _price);
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_position(_id integer, _id_supply integer, _id_spare_part integer,
	_amount integer, _price real)
    returns boolean as
$$
begin
    if exists(select * from "position" where id_position =_id) then
        update "position" set id_supply=_id_supply, id_spare_part=_id_spare_part,
		amount=_amount, price=_price where id_position=_id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_position(_id_position integer)
    returns boolean as
$$
begin
    if exists(select * from "position" where id_position =_id_position) then
		delete from "contract" where id_position =_id_position;
		return true;
    else return false;
    end if;
end;
$$
language plpgsql;