package com.laba.store.services;

import com.laba.store.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class DataBaseService {

    @Autowired
    private DataSource dataSource;

    private Connection connection;

    /*General methods*/

    private Connection connectDb() {
        try {
            connection = dataSource.getConnection();
        }
        catch (Exception e) {
            System.out.println("Ошибка подключения к бд: " + e);
        }
        return connection;
    }

    private ResultSet getDataFromDb(String request) {
        ResultSet resultSet = null;
        PreparedStatement statement;
        try {
            statement = connectDb().prepareStatement(request);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + request);
        }
        return resultSet;
    }

    private boolean boolResponse(String request){
        ResultSet resultSet = getDataFromDb(request);
        boolean response = false;
        try {
            while (resultSet.next()){
                response = resultSet.getBoolean(1);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения Stoke : " + e);
        }
        return response;
    }

    /*Stoke (Storehouse) methods*/

    public ArrayList<Stoke> getAllStock() {
        ArrayList<Stoke> arrayList= new ArrayList<>();
        ResultSet resultSet = getDataFromDb("select * from storehouse;");
        try {
            Stoke stoke;
            while(resultSet.next()){
                stoke = new Stoke();
                stoke.setId(Long.valueOf(resultSet.getString(1)));
                stoke.setLocation(resultSet.getString(2));
                stoke.setCount(resultSet.getInt(3));
                arrayList.add(stoke);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllStock : " + e);
        }
        return arrayList;
    }

    public boolean addStokeRequest(Stoke stoke) {
        String request = "select add_stoke("+stoke.getLocation()+","+stoke.getCount()+";";
        return boolResponse(request);
    }

    public boolean saveStokeRequest(Stoke stoke) {
        String request = "select update_stoke("+stoke.getLocation()+","+stoke.getCount()+";";
        return boolResponse(request);
    }

    public boolean deleteStokeRequest(Stoke stoke) {
        String request = "select del_stoke("+stoke.getId()+")";
        return boolResponse(request);
    }

    /*Section methods*/

    public ArrayList<Section> getAllSectionsRequest() {
        ArrayList<Section> arrayList= new ArrayList<>();
        String request = "select * from section;";
        ResultSet resultSet = getDataFromDb(request);
        try {
            Section section;
            while(resultSet.next()){
                section = new Section();
                section.setId(Long.valueOf(resultSet.getString(1)));
                section.setId_stoke(Long.valueOf(resultSet.getString(2)));
                section.setAmount(resultSet.getInt(3));
                section.setCapacity(resultSet.getInt(4));
                arrayList.add(section);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllSectionsRequest : " + e);
        }
        return arrayList;
    }

    public ArrayList<Section> getSectionsFromStock(Long id_stoke) {
        ArrayList<Section> arrayList= new ArrayList<>();
        String request = "select * from section where id_storehouse="+id_stoke+";";
        ResultSet resultSet = getDataFromDb(request);
        try {
            Section section;
            while(resultSet.next()){
                section = new Section();
                section.setId(Long.valueOf(resultSet.getString(1)));
                section.setId_stoke(Long.valueOf(resultSet.getString(2)));
                section.setAmount(resultSet.getInt(3));
                section.setCapacity(resultSet.getInt(4));
                arrayList.add(section);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getSectionsFromStock : " + e);
        }
        return arrayList;
    }

    public boolean addSectionRequest(Section section) {
        String request = "select add_section("+section.getId_stoke()+","+section.getCapacity()+","+section.getNumber()+")";
        return boolResponse(request);
    }

    public boolean saveSectionRequest(Section section) {
        String request = "select update_stoke("+section.getAmount()+","+section.getNumber()+";";
        return boolResponse(request);
    }

    public boolean deleteSectionRequest(Section section) {
        String request = "select delete_section("+section.getId()+")";
        return boolResponse(request);
    }

    /*Type methods*/

    public ArrayList<Type> getAllTypes() {
        ArrayList<Type> arrayList= new ArrayList<>();
        String request = "select * from type;";
        ResultSet resultSet = getDataFromDb(request);
        try {
            Type type;
            while(resultSet.next()){
                type = new Type();
                type.setId(Long.getLong(resultSet.getString(1)));
                type.setName(resultSet.getString(2));
                arrayList.add(type);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllTypes : " + e);
        }
        return arrayList;
    }

    public boolean addTypeRequest(Type type) {
        String request = "select add_type("+type.getName()+")";
        return boolResponse(request);
    }

    public boolean saveTypeRequest(Type type) {
        String request = "select update_type("+type.getId()+","+type.getName()+")";
        return boolResponse(request);
    }

    public boolean deleteTypeRequest(Type type) {
        String request = "select del_type("+type.getId()+")";
        return boolResponse(request);
    }

    /*Spate_part methods*/

    public ArrayList<Spare_part> getAllSparePartRequest() {
        ArrayList<Spare_part> arrayList= new ArrayList<>();
        String request = "select * from spare_part;";
        ResultSet resultSet = getDataFromDb(request);
        try {
            Spare_part spare_part;
            while(resultSet.next()){
                spare_part = new Spare_part();
                spare_part.setId(Long.getLong(resultSet.getString(1)));
                spare_part.setId_type(Long.getLong(resultSet.getString(2)));
                spare_part.setId_section(Long.getLong(resultSet.getString(3)));
                spare_part.setName(resultSet.getString(4));
                spare_part.setPrice(resultSet.getDouble(5));
                spare_part.setProportions(resultSet.getString(6));
                arrayList.add(spare_part);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllTypes : " + e);
        }
        return arrayList;
    }

    public boolean addSparePartRequest(Spare_part spare_part) {
        String request = "select add_spare_part("
            +spare_part.getId_type()+","
            +spare_part.getId_section()+","
            +spare_part.getName()+","
            +spare_part.getPrice()
            +spare_part.getProportions()+")";
        return boolResponse(request);
    }

    public boolean saveSparePartRequest(Spare_part spare_part) {
        String request = "select update_spare_part("
            +spare_part.getId()+","
            +spare_part.getId_type()+","
            +spare_part.getId_section()+","
            +spare_part.getName()+","
            +spare_part.getPrice()
            +spare_part.getProportions()+")";
        return boolResponse(request);
    }

    public boolean deleteSparePartRequest(Spare_part spare_part) {
        String request = "select del_section(" + spare_part.getId() + ")";
        return boolResponse(request);
    }

    /*Category*/

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> arrayList= new ArrayList<>();
        String request = "select * from category;";
        ResultSet resultSet = getDataFromDb(request);
        try {
            Category category;
            while(resultSet.next()){
                category = new Category();
                category.setId(Long.getLong(resultSet.getString(1)));
                category.setName(resultSet.getString(2));
                arrayList.add(category);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllTypes : " + e);
        }
        return arrayList;
    }

    public boolean addCategoryRequest(Category category) {
        String request = "select add_section("+category.getName()+")";
        return boolResponse(request);
    }

    public boolean saveCategoryRequest(Category category) {
        String request = "select update_section("+category.getId()+","+category.getName()+")";
        return boolResponse(request);
    }

    public boolean deleteCategoryRequest(Category category) {
        String request = "select del_section("+category.getId()+")";
        return boolResponse(request);
    }

    /*Country*/

    public ArrayList<Country> getAllCountries() {
        ArrayList<Country> arrayList= new ArrayList<>();
        String request = "select * from country;";
        ResultSet resultSet = getDataFromDb(request);
        try {
            Country country;
            while(resultSet.next()){
                country = new Country();
                country.setId(Long.getLong(resultSet.getString(1)));
                country.setName(resultSet.getString(2));
                arrayList.add(country);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllCountries : " + e);
        }
        return arrayList;
    }

    public boolean addCountryRequest(Country country) {
        String request = "select add_section("+country.getName()+")";
        return boolResponse(request);
    }

    public boolean saveCountryRequest(Country country) {
        String request = "select update_section("+country.getId()+","+country.getName()+")";
        return boolResponse(request);
    }

    public boolean deleteCountryRequest(Country country) {
        String request = "select del_section("+country.getId()+")";
        return boolResponse(request);
    }

    /*Purveyor*/

    public ArrayList<Purveyor> getAllPurveyorRequest() {
        ArrayList<Purveyor> arrayList= new ArrayList<>();
        String request = "select * from purveyor;";
        ResultSet resultSet = getDataFromDb(request);
        try {
            Purveyor purveyor;
            while(resultSet.next()){
                purveyor = new Purveyor();
                purveyor.setId(Long.getLong(resultSet.getString(1)));
                purveyor.setId_category(Long.getLong(resultSet.getString(2)));
                purveyor.setId_country(Long.getLong(resultSet.getString(3)));
                purveyor.setWarranty(resultSet.getString(4));
                purveyor.setName(resultSet.getString(5));
                purveyor.setAdress(resultSet.getString(6));
                arrayList.add(purveyor);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllPurveyorRequest : " + e);
        }
        return arrayList;
    }

    public boolean addPurveyorRequest(Purveyor purveyor) {
        String request = "select add_purveyor("
            +purveyor.getId_category()+","
            +purveyor.getId_country()+","
            +purveyor.getWarranty()+","
            +purveyor.getName()+","
            +purveyor.getAdress()+")";
        return boolResponse(request);
    }

    public boolean savePurveyorRequest(Purveyor purveyor) {
        String request = "select update_section("
            +purveyor.getId()+","
            +purveyor.getId_category()+","
            +purveyor.getId_country()+","
            +purveyor.getWarranty()+","
            +purveyor.getName()+","
            +purveyor.getAdress()+")";
        return boolResponse(request);
    }

    public boolean deletePurveyorRequest(Purveyor purveyor) {
        String request = "select del_section("+purveyor.getId()+")";
        return boolResponse(request);
    }

    /* Supply */

    public ArrayList<Supply> getAllSupplyRequest() {
        ArrayList<Supply> arrayList= new ArrayList<>();
        String request = "select * from supply;";
        ResultSet resultSet = getDataFromDb(request);
        try {
            Supply supply;
            while(resultSet.next()){
                supply = new Supply();
                supply.setId(Long.getLong(resultSet.getString(1)));
                supply.setId_contract(Long.getLong(resultSet.getString(2)));
                supply.setId_purveyor(Long.getLong(resultSet.getString(3)));
                supply.setDate(LocalDateTime.parse(resultSet.getString(4)));
                supply.setTax(resultSet.getDouble(5));
                arrayList.add(supply);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllSupplyRequest : " + e);
        }
        return arrayList;
    }

    public boolean addSupplyRequest(Supply supply) {
        String request = "select add_supply("
            +supply.getId_contract()+","
            +supply.getId_purveyor()+","
            +supply.getDate()+","
            +supply.getTax()+")";
        return boolResponse(request);
    }

    public boolean saveSupplyRequest(Supply supply) {
        String request = "select update_supply("
            +supply.getId()+","
            +supply.getId_contract()+","
            +supply.getId_purveyor()+","
            +supply.getDate()+","
            +supply.getTax()+")";
        return boolResponse(request);
    }

    public boolean deleteSupplyRequest(Supply supply) {
        String request = "select del_supply("+supply.getId()+")";
        return boolResponse(request);
    }

    /* Contract */

    public ArrayList<Contract> getAllContractRequest() {
        ArrayList<Contract> arrayList= new ArrayList<>();
        String request = "select * from contract;";
        ResultSet resultSet = getDataFromDb(request);
        try {
            Contract contract;
            while(resultSet.next()){
                contract = new Contract();
                contract.setId(Long.getLong(resultSet.getString(1)));
                contract.setMember_one(resultSet.getString(2));
                contract.setMember_two(resultSet.getString(3));
                contract.setBody(resultSet.getString(4));
                contract.setDate(LocalDateTime.parse(resultSet.getString(5)));
                arrayList.add(contract);
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка выполнения getAllContractRequest : " + e);
        }
        return arrayList;
    }

    public boolean addContractRequest(Contract contract) {
        String request = "select add_contract("
            +contract.getMember_one()+","
            +contract.getMember_two()+","
            +contract.getBody()+","
            +contract.getDate()+")";
        return boolResponse(request);
    }

    public boolean saveContractRequest(Contract contract) {
        String request = "select update_contract("
            +contract.getId()+","
            +contract.getMember_one()+","
            +contract.getMember_two()+","
            +contract.getBody()+","
            +contract.getDate()+")";
        return boolResponse(request);
    }

    public boolean deleteContractRequest(Contract contract) {
        String request = "select del_contract("+contract.getId()+")";
        return boolResponse(request);
    }

    /* Position */

    public ArrayList<Position> getPositionFromSupply(Long id_supply) {
        ArrayList<Position> arrayList= new ArrayList<>();
        String request = "select * from position where id_supply="+id_supply+";";
        ResultSet resultSet = getDataFromDb(request);
        try {
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
            System.out.println("Ошибка выполнения getPositionFromSupply : " + e);
        }
        return arrayList;
    }

    public boolean addPositionRequest(Position position) {
        String request = "select add_position("
            +position.getId_supply()+","
            +position.getId_spare_part()+","
            +position.getAmount()+","
            +position.getPrice()+")";
        return boolResponse(request);
    }

    public boolean savePositionRequest(Position position) {
        String request = "select update_position("
            +position.getId()+","
            +position.getId_supply()+","
            +position.getId_spare_part()+","
            +position.getAmount()+","
            +position.getPrice()+")";
        return boolResponse(request);
    }

    public boolean deletePositionRequest(Position position) {
        String request = "select del_position("+position.getId()+")";
        return boolResponse(request);
    }
}