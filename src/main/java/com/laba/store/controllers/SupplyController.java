package com.laba.store.controllers;

import com.laba.store.domain.Supply;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("supply")
public class SupplyController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Supply> getAllSupply() throws SQLException { return dataBaseService.getAllSupplyRequest(); }

  @PostMapping
  public HashMap<String, String> addSupply(@RequestBody Supply supply) throws SQLException { return dataBaseService.addSupplyRequest(supply); }

  @PutMapping
  public HashMap<String, String> saveSupply(@RequestBody Supply supply) throws SQLException { return dataBaseService.saveSupplyRequest(supply); }

  @PostMapping("delete")
  public HashMap<String, String> deleteSupply(@RequestBody Supply supply) throws SQLException { return dataBaseService.deleteSupplyRequest(supply); }
}
