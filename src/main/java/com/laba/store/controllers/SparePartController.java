package com.laba.store.controllers;

import com.laba.store.domain.Spare_part;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("part")
public class SparePartController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Spare_part> getAllSparePart() throws SQLException { return dataBaseService.getAllSparePartRequest(); }

  @PostMapping
  public HashMap<String, String> addSparePart(@RequestBody Spare_part spare_part) throws SQLException { return dataBaseService.addSparePartRequest(spare_part); }

  @PutMapping
  public HashMap<String, String> saveSparePart(@RequestBody Spare_part spare_part) throws SQLException { return dataBaseService.saveSparePartRequest(spare_part); }

  @PostMapping("delete")
  public HashMap<String, String> deleteSparePart(@RequestBody Spare_part spare_part) throws SQLException { return dataBaseService.deleteSparePartRequest(spare_part); }
}
