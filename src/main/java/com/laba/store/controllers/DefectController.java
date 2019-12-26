package com.laba.store.controllers;

import com.laba.store.domain.Defect;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("defect")
public class DefectController {
  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Defect> getAllDefect() throws SQLException { return dataBaseService.getAllDefectRequest(); }

  @PostMapping
  public HashMap<String, String> addDefect(@RequestBody Defect defect) throws SQLException { return dataBaseService.addDefectRequest(defect); }

  @PutMapping
  public HashMap<String, String> saveDefect(@RequestBody Defect defect) throws SQLException { return dataBaseService.saveDefectRequest(defect); }

  @PostMapping("delete")
  public HashMap<String, String> deleteDefect(@RequestBody Defect defect) throws SQLException { return dataBaseService.deleteDefectRequest(defect); }
}
