package com.laba.store.controllers;

import com.laba.store.domain.Purveyor;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("purveyor")
public class PurveyorController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Purveyor> getAll() throws SQLException { return dataBaseService.getAllPurveyorRequest(); }

  @PostMapping
  public HashMap<String, String> addPurveyor(@RequestBody Purveyor purveyor) throws SQLException { return dataBaseService.addPurveyorRequest(purveyor); }

  @PutMapping
  public HashMap<String, String> savePurveyor(@RequestBody Purveyor purveyor) throws SQLException { return dataBaseService.savePurveyorRequest(purveyor); }

  @PostMapping("delete")
  public HashMap<String, String> deletePurveyor(@RequestBody Purveyor purveyor) throws SQLException { return dataBaseService.deletePurveyorRequest(purveyor); }
}
