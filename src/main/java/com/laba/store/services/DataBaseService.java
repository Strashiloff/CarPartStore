package com.laba.store.services;

import com.laba.store.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimeZone;

@Service
public class DataBaseService {

    @Autowired
    private DataSource dataSource;

    /*General methods*/

    private Connection connectDb() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        }
        catch (Exception e) {
            System.out.println("Ошибка подключения к бд: " + e.getMessage());
        }
        return connection;
    }

    private HashMap<String, Boolean> boolResponse(String request) throws SQLException {
        boolean response = false;
        HashMap<String, Boolean> map = new HashMap<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                response = resultSet.getBoolean(1);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения boolResponse: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        map.put("okey", response);
        return map;
    }

    /*Stoke (Storehouse) methods*/

    public ArrayList<Stoke> getAllStock() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Stoke> arrayList= new ArrayList<>();
        try {
            connection = connectDb();
            statement = connection.prepareStatement("select * from storehouse order by id_storehouse ASC;");
            ResultSet resultSet = statement.executeQuery();
            Stoke stoke;
            while(resultSet.next()){
                stoke = new Stoke();
                stoke.setId(resultSet.getInt(1));
                stoke.setLocation(resultSet.getString(2));
                stoke.setCount(resultSet.getInt(3));
                arrayList.add(stoke);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllStock: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addStokeRequest(Stoke stoke) throws SQLException {
        String request = "select add_stoke('"+stoke.getLocation()+"',"+stoke.getCount()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveStokeRequest(Stoke stoke) throws SQLException {
        String request = "select update_stoke("+stoke.getId()+",'"+stoke.getLocation()+"',"+stoke.getCount()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteStokeRequest(Stoke stoke) throws SQLException {
        String request = "select del_stoke("+stoke.getId()+")";
        return boolResponse(request);
    }

    /*Section methods*/

    public ArrayList<Section> getAllSectionsRequest() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Section> arrayList= new ArrayList<>();
        String request = "select * from section order by id_section ASC;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Section section;
            while(resultSet.next()){
                section = new Section();
                section.setId(Long.valueOf(resultSet.getString(1)));
                section.setId_stoke(Long.valueOf(resultSet.getString(2)));
                section.setAmount(resultSet.getInt(3));
                section.setCapacity(resultSet.getInt(4));
                section.setNumber(resultSet.getInt(5));
                arrayList.add(section);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllSectionsRequest: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public ArrayList<Section> getSectionsFromStock(int id_stoke) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Section> arrayList= new ArrayList<>();
        String request = "select * from section where id_storehouse="+id_stoke+" order by id_section ASC;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Section section;
            while(resultSet.next()){
                section = new Section();
                section.setId(Long.valueOf(resultSet.getString(1)));
                section.setId_stoke(Long.valueOf(resultSet.getString(2)));
                section.setAmount(resultSet.getInt(3));
                section.setCapacity(resultSet.getInt(4));
                System.out.println(resultSet.getInt(5));
                section.setNumber(resultSet.getInt(5));
                arrayList.add(section);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getSectionsFromStock: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addSectionRequest(Section section) throws SQLException {
        String request = "select add_section("+section.getId_stoke()+","+section.getCapacity()+","+section.getNumber()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveSectionRequest(Section section) throws SQLException {
        String request = "select update_section("+section.getId()+","+section.getAmount()+","+section.getCapacity()+","+section.getNumber()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteSectionRequest(Section section) throws SQLException {
        String request = "select del_section("+section.getId()+")";
        return boolResponse(request);
    }

    /*Type methods*/

    public ArrayList<Type> getAllTypes() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Type> arrayList= new ArrayList<>();
        String request = "select * from type order by id_type ASC;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Type type;
            while(resultSet.next()){
                type = new Type();
                type.setId(Long.valueOf(resultSet.getString(1)));
                type.setName(resultSet.getString(2));
                arrayList.add(type);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllTypes: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addTypeRequest(Type type) throws SQLException {
        String request = "select add_type('"+type.getName()+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveTypeRequest(Type type) throws SQLException {
        String request = "select update_type("+type.getId()+",'"+type.getName()+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteTypeRequest(Type type) throws SQLException {
        String request = "select del_type("+type.getId()+")";
        return boolResponse(request);
    }

    /*Spate_part methods*/

    public ArrayList<Spare_part> getAllSparePartRequest() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Spare_part> arrayList= new ArrayList<>();
        String request = "select * from spare_part order by id_spare_part ASC;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Spare_part spare_part;
            while(resultSet.next()){
                spare_part = new Spare_part();
                spare_part.setId(Long.valueOf(resultSet.getString(1)));
                spare_part.setId_type(Long.valueOf(resultSet.getString(2)));
                spare_part.setId_section(Long.valueOf(resultSet.getString(3)));
                spare_part.setName(resultSet.getString(4));
                spare_part.setPrice(resultSet.getDouble(6));
                spare_part.setProportions(resultSet.getString(5));
                arrayList.add(spare_part);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllSparePartRequest: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addSparePartRequest(Spare_part spare_part) throws SQLException {
        String request = "select add_spare_part("
            +spare_part.getId_type()+","
            +spare_part.getId_section()+",'"
            +spare_part.getName()+"',"
            +spare_part.getPrice()+",'"
            +spare_part.getProportions()+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveSparePartRequest(Spare_part spare_part) throws SQLException {
        String request = "select update_spare_part("
            +spare_part.getId()+","
            +spare_part.getId_type()+","
            +spare_part.getId_section()+",'"
            +spare_part.getName()+"',"
            +spare_part.getPrice()+",'"
            +spare_part.getProportions()+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteSparePartRequest(Spare_part spare_part) throws SQLException {
        String request = "select del_spare_part(" + spare_part.getId() + ")";
        return boolResponse(request);
    }

    /*Category*/

    public ArrayList<Category> getAllCategories() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Category> arrayList= new ArrayList<>();
        String request = "select * from category order by id_category ASC;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Category category;
            while(resultSet.next()){
                category = new Category();
                category.setId(Long.valueOf(resultSet.getString(1)));
                category.setName(resultSet.getString(2));
                arrayList.add(category);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllCategories: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addCategoryRequest(Category category) throws SQLException {
        String request = "select add_category('"+category.getName()+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveCategoryRequest(Category category) throws SQLException {
        String request = "select update_category("+category.getId()+",'"+category.getName()+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteCategoryRequest(Category category) throws SQLException {
        String request = "select del_category("+category.getId()+")";
        return boolResponse(request);
    }

    /*Country*/

    public ArrayList<Country> getAllCountries() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Country> arrayList= new ArrayList<>();
        String request = "select * from country order by id_country ASC;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Country country;
            while(resultSet.next()){
                country = new Country();
                country.setId(Long.valueOf(resultSet.getString(1)));
                country.setName(resultSet.getString(2));
                arrayList.add(country);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllCountries: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addCountryRequest(Country country) throws SQLException {
        String request = "select add_country('"+country.getName()+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveCountryRequest(Country country) throws SQLException {
        String request = "select update_country("+country.getId()+",'"+country.getName()+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteCountryRequest(Country country) throws SQLException {
        String request = "select del_country("+country.getId()+")";
        return boolResponse(request);
    }

    /*Purveyor*/

    public ArrayList<Purveyor> getAllPurveyorRequest() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Purveyor> arrayList= new ArrayList<>();
        String request = "select * from purveyor order by id_purveyor ASC;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Purveyor purveyor;
            while(resultSet.next()){
                purveyor = new Purveyor();
                purveyor.setId(Long.valueOf(resultSet.getString(1)));
                purveyor.setId_category(Long.valueOf(resultSet.getString(2)));
                purveyor.setId_country(Long.valueOf(resultSet.getString(3)));
                purveyor.setWarranty(resultSet.getString(4));
                purveyor.setName(resultSet.getString(5));
                purveyor.setAdress(resultSet.getString(6));
                arrayList.add(purveyor);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllPurveyorRequest: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addPurveyorRequest(Purveyor purveyor) throws SQLException {
        String request = "select add_purveyor("
            +purveyor.getId_category()+","
            +purveyor.getId_country()+",'"
            +purveyor.getWarranty()+"','"
            +purveyor.getName()+"','"
            +purveyor.getAdress()+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> savePurveyorRequest(Purveyor purveyor) throws SQLException {
        String request = "select update_purveyor("
            +purveyor.getId()+","
            +purveyor.getId_category()+","
            +purveyor.getId_country()+",'"
            +purveyor.getWarranty()+"','"
            +purveyor.getName()+"','"
            +purveyor.getAdress()+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deletePurveyorRequest(Purveyor purveyor) throws SQLException {
        String request = "select del_purveyor("+purveyor.getId()+")";
        return boolResponse(request);
    }

    /* Supply */

    public ArrayList<Supply> getAllSupplyRequest() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Supply> arrayList= new ArrayList<>();
        String request = "select * from supply order by id_supply DESC;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Supply supply;
            while(resultSet.next()){
                supply = new Supply();
                supply.setId(Long.valueOf(resultSet.getString(1)));
                try {
                    supply.setId_contract(Long.valueOf(resultSet.getString(2)));
                } catch (Exception e) {
                    supply.setId_contract(null);
                }
                supply.setId_purveyor(Long.valueOf(resultSet.getString(3)));
                Timestamp timestamp = Timestamp.valueOf(resultSet.getString(4));
                supply.setDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), TimeZone.getDefault().toZoneId()));
                supply.setTax(Double.valueOf(resultSet.getString(5)));
                arrayList.add(supply);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllSupplyRequest: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addSupplyRequest(Supply supply) throws SQLException {
        String request = "select add_supply("
            +supply.getId_contract()+","
            +supply.getId_purveyor()+",'"
            +Timestamp.valueOf(supply.getDate())+"',"
            +supply.getTax()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveSupplyRequest(Supply supply) throws SQLException {
        String request = "select update_supply("
            +supply.getId()+","
            +supply.getId_contract()+","
            +supply.getId_purveyor()+",'"
            +Timestamp.valueOf(supply.getDate())+"',"
            +supply.getTax()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteSupplyRequest(Supply supply) throws SQLException {
        String request = "select del_supply("+supply.getId()+")";
        return boolResponse(request);
    }

    /* Contract */

    public ArrayList<Contract> getAllContractRequest() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Contract> arrayList= new ArrayList<>();
        String request = "select * from contract order by id_contract DESC;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Contract contract;
            while(resultSet.next()){
                contract = new Contract();
                contract.setId(Long.valueOf(resultSet.getString(1)));
                contract.setBody(resultSet.getString(2));
                Timestamp timestamp = Timestamp.valueOf(resultSet.getString(3));
                contract.setDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), TimeZone.getDefault().toZoneId()));
                arrayList.add(contract);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllContractRequest: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addContractRequest(Contract contract) throws SQLException {
        String request = "select add_contract('"
            +contract.getBody()+"','"
            +Timestamp.valueOf(contract.getDate())+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveContractRequest(Contract contract) throws SQLException {
        String request = "select update_contract("
            +contract.getId()+",'"
            +contract.getBody()+"','"
            +Timestamp.valueOf(contract.getDate())+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteContractRequest(Contract contract) throws SQLException {
        String request = "select del_contract("+contract.getId()+")";
        return boolResponse(request);
    }

    /* Position */

    public ArrayList<Position> getPositionFromSupply() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Position> arrayList= new ArrayList<>();
        String request = "select * from position order by id_position ASC;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Position position;
            while(resultSet.next()){
                position = new Position();
                position.setId(Long.valueOf(resultSet.getString(1)));
                position.setId_supply(Long.valueOf(resultSet.getString(2)));
                position.setId_spare_part(Long.valueOf(resultSet.getString(3)));
                position.setAmount(resultSet.getInt(4));
                position.setPrice(resultSet.getDouble(5));
                arrayList.add(position);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getPositionFromSupply : " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addPositionRequest(Position position) throws SQLException {
        String request = "select add_position("
            +position.getId_supply()+","
            +position.getId_spare_part()+","
            +position.getAmount()+","
            +position.getPrice()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> savePositionRequest(Position position) throws SQLException {
        String request = "select update_position("
            +position.getId()+","
            +position.getId_supply()+","
            +position.getId_spare_part()+","
            +position.getAmount()+","
            +position.getPrice()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deletePositionRequest(Position position) throws SQLException {
        String request = "select del_position("+position.getId()+")";
        return boolResponse(request);
    }

    /* List */

    public ArrayList<List> getListFromRequest() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<List> arrayList= new ArrayList<>();
        String request = "select * from list order by id_list ASC;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            List list;
            while(resultSet.next()){
                list = new List();
                list.setId(Long.valueOf(resultSet.getString(1)));
                list.setId_request(Long.valueOf(resultSet.getString(2)));
                list.setId_position(Long.valueOf(resultSet.getString(3)));
                list.setAmount(resultSet.getInt(4));
                list.setPrice(resultSet.getDouble(5));
                arrayList.add(list);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getListFromRequest : " + e);
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addListRequest(List list) throws SQLException {
        String request = "select add_list("
            +list.getId_request()+","
            +list.getId_position()+","
            +list.getAmount()+","
            +list.getPrice()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveListRequest(List list) throws SQLException {
        String request = "select update_list("
            +list.getId()+","
            +list.getId_request()+","
            +list.getId_position()+","
            +list.getAmount()+","
            +list.getPrice()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteListRequest(List list) throws SQLException {
        String request = "select del_list("+list.getId()+")";
        return boolResponse(request);
    }

    /* Request */

    public ArrayList<Request> getAllRequestRequest() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Request> arrayList= new ArrayList<>();
        String request = "select * from request;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Request request1;
            while(resultSet.next()){
                request1 = new Request();
                request1.setId(resultSet.getLong(1));
                request1.setId_customer(resultSet.getLong(2));
                Timestamp timestamp = Timestamp.valueOf(resultSet.getString(3));
                request1.setDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), TimeZone.getDefault().toZoneId()));
                timestamp = Timestamp.valueOf(resultSet.getString(4));
                request1.setPeriod(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), TimeZone.getDefault().toZoneId()));
                request1.setId_user(Long.valueOf(resultSet.getString(5)));
                arrayList.add(request1);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllRequestRequest : " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public ArrayList<Request> getRequestFromBuy(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Request> arrayList= new ArrayList<>();
        String request = "select * from request where id_request="+id+";";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Request request1;
            while(resultSet.next()){
                request1 = new Request();
                request1.setId(Long.valueOf(resultSet.getString(1)));
                request1.setId_user(Long.valueOf(resultSet.getString(2)));
                request1.setId_customer(Long.valueOf(resultSet.getString(3)));
                request1.setDate(LocalDateTime.parse(resultSet.getString(4)));
                request1.setPeriod(LocalDateTime.parse(resultSet.getString(5)));
                arrayList.add(request1);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getRequestFromBuy: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addRequestRequest(Request req) throws SQLException {
        String request = "select add_request("
            +req.getId_user()+","
            +req.getId_customer()+",'"
            +Timestamp.valueOf(req.getDate())+"','"
            +Timestamp.valueOf(req.getPeriod())+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveRequestRequest(Request req) throws SQLException {
        String request = "select update_request("
            +req.getId()+","
            +req.getId_user()+","
            +req.getId_customer()+",'"
            +Timestamp.valueOf(req.getDate())+"','"
            +Timestamp.valueOf(req.getPeriod())+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteRequestRequest(Request req) throws SQLException {
        String request = "select del_request("+req.getId()+")";
        return boolResponse(request);
    }

    /* Buy */

    public ArrayList<Buy> getAllBuyRequest() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Buy> arrayList= new ArrayList<>();
        String request = "select * from buy;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Buy buy;
            while(resultSet.next()){
                buy = new Buy();
                buy.setId(resultSet.getLong(1));
                buy.setId_request(resultSet.getLong(2));
                Timestamp timestamp = Timestamp.valueOf(resultSet.getString(3));
                buy.setDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), TimeZone.getDefault().toZoneId()));
                buy.setCompleted(resultSet.getBoolean(4));
                arrayList.add(buy);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllBuyRequest: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public ArrayList<Buy> getBuyFromDefect(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Buy> arrayList= new ArrayList<>();
        String request = "select * from request where id_request="+id+";";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Buy buy;
            while(resultSet.next()){
                buy = new Buy();
                buy.setId(Long.valueOf(resultSet.getString(1)));
                buy.setCompleted(Boolean.parseBoolean(resultSet.getString(2)));
                buy.setDate(LocalDateTime.parse(resultSet.getString(3)));
                buy.setDate(LocalDateTime.parse(resultSet.getString(4)));
                arrayList.add(buy);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getBuyFromDefect: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addBuyRequest(Buy buy) throws SQLException {
        String request = "select add_buy("
            +buy.getId_request()+","
            +buy.isCompleted()+",'"
            +Timestamp.valueOf(buy.getDate())+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveBuyRequest(Buy buy) throws SQLException {
        String request = "select update_buy("
            +buy.getId()+","
            +buy.getId_request()+","
            +buy.isCompleted()+",'"
            +Timestamp.valueOf(buy.getDate())+"')";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteBuyRequest(Buy buy) throws SQLException {
        String request = "select del_buy("+buy.getId()+")";
        return boolResponse(request);
    }

    /* Defect */

    public ArrayList<Defect> getAllDefectRequest() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Defect> arrayList= new ArrayList<>();
        String request = "select * from defect;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Defect defect;
            while(resultSet.next()){
                defect = new Defect();
                defect.setId(resultSet.getLong(1));
                defect.setId_buy(resultSet.getLong(2));
                defect.setId_list(resultSet.getLong(3));
                Timestamp timestamp = Timestamp.valueOf(resultSet.getString(4));
                defect.setDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), TimeZone.getDefault().toZoneId()));
                defect.setAmount(resultSet.getInt(5));
                arrayList.add(defect);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllDefectRequest: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addDefectRequest(Defect defect) throws SQLException {
        String request = "select add_defect("
            +defect.getId_buy()+","
            +defect.getId_list()+",'"
            +Timestamp.valueOf(defect.getDate())+"',"
            +defect.getAmount()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveDefectRequest(Defect defect) throws SQLException {
        String request = "select update_defect("
            +defect.getId()+","
            +defect.getId_buy()+","
            +defect.getId_list()+",'"
            +Timestamp.valueOf(defect.getDate())+"',"
            +defect.getAmount()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteDefectRequest(Defect defect) throws SQLException {
        String request = "select del_defect("+defect.getId()+")";
        return boolResponse(request);
    }

    /* Customer */

    public ArrayList<Customer> getAllCustomerRequest() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Customer> arrayList= new ArrayList<>();
        String request = "select * from customer;";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Customer customer;
            while(resultSet.next()){
                customer = new Customer();
                customer.setId(Long.valueOf(resultSet.getString(1)));
                customer.setSurname(resultSet.getString(2));
                customer.setName(resultSet.getString(3));
                customer.setPatronymic(resultSet.getString(4));
                customer.setContact(resultSet.getString(5));
                customer.setSex(resultSet.getBoolean(6));
                arrayList.add(customer);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllCustomerRequest: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public ArrayList<Customer> getCustomerFromRequest(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Customer> arrayList= new ArrayList<>();
        String request = "select * from customer where id_customer="+ id + ";";
        try {
            connection = connectDb();
            statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            Customer customer;
            while(resultSet.next()){
                customer = new Customer();
                customer.setId(Long.valueOf(resultSet.getString(1)));
                customer.setSurname(resultSet.getString(2));
                customer.setName(resultSet.getString(3));
                customer.setPatronymic(resultSet.getString(4));
                customer.setContact(resultSet.getString(5));
                customer.setSex(Boolean.parseBoolean(resultSet.getString(6)));
                arrayList.add(customer);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getCustomerFromRequest: " + e.getMessage());
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return arrayList;
    }

    public HashMap<String, Boolean> addCustomerRequest(Customer customer) throws SQLException {
        String request = "select add_customer('"
            +customer.getSurname()+"','"
            +customer.getName()+"','"
            +customer.getPatronymic()+"','"
            +customer.getContact()+"',"
            +customer.isSex()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> saveCustomerRequest(Customer customer) throws SQLException {
        String request = "select update_customer("
            +customer.getId()+",'"
            +customer.getSurname()+"','"
            +customer.getName()+"','"
            +customer.getPatronymic()+"','"
            +customer.getContact()+"',"
            +customer.isSex()+")";
        return boolResponse(request);
    }

    public HashMap<String, Boolean> deleteCustomerRequest(Customer customer) throws SQLException {
        String request = "select del_customer("+customer.getId()+")";
        return boolResponse(request);
    }
}