package com.laba.store.controllers;

import com.laba.store.domain.Type;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("type")
public class TypeController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Type> getAll() throws SQLException { return dataBaseService.getAllTypes(); }

  @PostMapping
  public HashMap<String, Boolean> addType(@RequestBody Type type) throws SQLException { return dataBaseService.addTypeRequest(type); }

  @PutMapping
  public HashMap<String, Boolean> saveType(@RequestBody Type type) throws SQLException { return dataBaseService.saveTypeRequest(type); }

  @PostMapping("delete")
  public HashMap<String, Boolean> deleteType(@RequestBody Type type) throws SQLException { return dataBaseService.deleteTypeRequest(type); }
}
