package com.laba.store.services;

import com.laba.store.domain.Section;
import com.laba.store.domain.Spare_part;
import com.laba.store.domain.Stoke;
import com.laba.store.domain.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String request = "select add_section("+type.getName()+")";
        return boolResponse(request);
    }

    public boolean saveTypeRequest(Type type) {
        String request = "select update_section("+type.getId()+","+type.getName()+")";
        return boolResponse(request);
    }

    public boolean deleteTypeRequest(Type type) {
        String request = "select del_section("+type.getId()+")";
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
}


