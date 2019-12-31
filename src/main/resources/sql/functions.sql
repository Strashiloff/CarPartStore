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

create or replace function update_section(_id integer, _amount integer, _max_amount integer, _number integer)
    returns boolean as
$$
begin
     if exists(select * from "section" where id_section = _id) then
        update "section" set amount=_amount, "max_amount" = _max_amount, "number" = _number where id_section = _id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_section(_id integer)
    returns boolean as
$$
begin
    if not exists(select * from "spare_part" where id_section = _id) then
        delete from "section" where id_section = _id;
        return true;
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
		return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_type(_id integer, _name character varying (255))
    returns boolean as
$$
begin
    if exists(select * from "type" where "name" = _name) then
        update "type" set "name" = _name where id_type = _id;
    	return true;
	else return false;
    end if;
end;

$$
language plpgsql;

create or replace function del_type(_id integer)
    returns boolean as
$$
begin
    if not exists(select * from "spare_part" where id_type = _id) then
        delete from "type" where id_type = _id;
		return true;
    else return false;
    end if;
end;

$$
language plpgsql;

/*
    spare_part
*/

create or replace function add_spare_part(_id_type integer, _id_section integer, _name varchar(500), _price double precision, _proportions varchar(500))
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

create or replace function update_spare_part(_id integer, _id_type integer, _id_section integer, _name varchar(500), _price double precision, _proportions varchar(500))
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
        return true;
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
        return true;
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
		return true;
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
    if not exists(select * from "supply" where id_purveyor = _id) then
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
	_data timestamp, _tax double precision)
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
	_data timestamp, _tax double precision)
    returns boolean as
$$
begin
    if exists(select * from "supply" where id_supply =_id_supply) then
        update "supply" set id_contract = _id_contract, id_purveyor =_id_purveyor,
		"date" = _data, tax = _tax where id_supply = _id_supply;
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

create or replace function add_contract(_body text, _date timestamp)
    returns boolean as
$$
begin
    if not exists(select * from "contract" where id_contract =_id_contract) then
        insert into "contract"(body, "date")
        values(_body, _date);
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_contract(_id integer, _body text, _date timestamp)
    returns boolean as
$$
begin
    if not exists(select * from "contract" where id_contract =_id) then
        update "contract" set body = _body, "date" = _date where id_contract = _id;
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
	_amount integer, _price double precision)
    returns boolean as
$$
begin
    if not exists(select * from "position" where id_spare_part =_id_spare_part and id_supply = _id_supply) then
        insert into "position"(id_supply, id_spare_part, amount, price)
        values(_id_supply, _id_spare_part, _amount, _price);
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_position(_id integer, _id_supply integer, _id_spare_part integer,
	_amount integer, _price double precision)
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
      delete from "position" where id_position =_id_position;
      return true;
    else return false;
    end if;
end;
$$
language plpgsql;

/* list */

create or replace function add_list(_id_request integer, _id_position integer,
	_amount integer, _price double precision)
    returns boolean as
$$
begin
    if not exists(select * from "list" where _id_position =_id_position) then
        insert into "list"(id_request, id_position, amount, price)
        values(_id_request, _id_position, _amount, _price);
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function update_list(_id integer, _id_request integer, _id_position integer,
	_amount integer, _price double precision)
    returns boolean as
$$
begin
    if exists(select * from "list" where id_list =_id) then
        update "list" set id_request=_id_request, id_position=_id_position,
		amount=_amount, price=_price where id_list=_id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_list(_id_list integer)
    returns boolean as
$$
begin
    if not exists(select * from "defect" where id_list =_id_list) then
		delete from "list" where id_list =_id_list;
		return true;
    else return false;
    end if;
end;
$$
language plpgsql;

/* request */

create or replace function add_request(_id_user integer, _id_customer integer,
	_date timestamp, _period timestamp)
    returns boolean as
$$
begin
	insert into "request"(id_user, id_customer, "date", "period")
	values(_id_user, _id_customer, _date, _period);
	return true;
end;
$$
language plpgsql;

create or replace function update_request(_id integer, _id_user integer, _id_customer integer,
	_date timestamp, _period timestamp)
    returns boolean as
$$
begin
    if exists(select * from "request" where id_request =_id) then
        update "request" set id_user=_id_user, id_customer=_id_customer,
		"date"=_date, "period"=_period where id_request=_id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_request(_id integer)
    returns boolean as
$$
begin
    if not exists(select * from "buy" where id_request =_id) then
		delete from "list" where id_request =_id;
		return true;
    else return false;
    end if;
end;
$$
language plpgsql;

/* buy */

create or replace function add_buy(_id_request integer, _completed boolean,
	_date timestamp)
    returns boolean as
$$
begin
  if not exists (select * from buy where id_request = _id_request) then
    insert into "buy"(id_request, completed, "date")
    values(_id_request, _completed, _date);
    return true;
   else return false;
   end if;
end;
$$
language plpgsql;

create or replace function update_buy(_id integer, _id_request integer, _completed integer,
	_date timestamp)
    returns boolean as
$$
begin
    if exists(select * from "buy" where id_buy =_id) then
        update "buy" set id_request=_id_request, completed=_completed,
		"date"=_date where id_buy=_id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_buy(_id integer)
    returns boolean as
$$
begin
    if not exists(select * from "defect" where id_buy =_id) then
		delete from "buy" where id_buy =_id;
		return true;
    else return false;
    end if;
end;
$$
language plpgsql;

/* buy */

create or replace function add_defect(_id_buy integer, _id_list integer, _date timestamp,
	_amount integer)
    returns boolean as
$$
begin
	insert into "defect"(id_buy, id_list, "date", amount)
	values(_id_buy, _id_list, _date, _amount);
	return true;
end;
$$
language plpgsql;

create or replace function update_defect(_id integer, _id_buy integer, _id_list integer, _date timestamp,
	_amount integer)
    returns boolean as
$$
begin
    if exists(select * from "defect" where id_defect =_id) then
        update "defect" set id_buy=_id_buy, _id_list=_id_list,
		"date"=_date, amount=_amount where id_defect=_id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_defect(_id integer)
    returns boolean as
$$
begin
    if exists(select * from "defect" where id_defect =_id) then
		delete from "defect" where id_defect =_id;
		return true;
    else return false;
    end if;
end;
$$
language plpgsql;

/* Customer */

create or replace function add_customer(_surname varchar(255), _name varchar(255), _patronymic varchar(255),
	_contact_number varchar(20), _sex boolean)
    returns boolean as
$$
begin
	if not exists(select * from "customer" where surname =_surname and "name" = _name and  patronymic=_patronymic) then
		insert into "customer"(surname, "name", patronymic, contact_number, sex)
		values(_surname, _name, _patronymic, _contact_number, _sex);
		return true;
	else return false;
	end if;
end;
$$
language plpgsql;

create or replace function update_customer(_id integer, _surname varchar(255), _name varchar(255), _patronymic varchar(255),
	_contact_number varchar(20), _sex boolean)
    returns boolean as
$$
begin
    if exists(select * from "customer" where id_customer =_id) then
        update "customer" set surname=_surname, "name"=_name,
		patronymic=_patronymic, contact_number=_contact_number, sex=_sex where id_customer=_id;
        return true;
    else return false;
    end if;
end;
$$
language plpgsql;

create or replace function del_customer(_id integer)
    returns boolean as
$$
begin
    if exists(select * from "customer" where id_customer =_id) then
		delete from "customer" where id_customer =_id;
		return true;
    else return false;
    end if;
end;
$$
language plpgsql;