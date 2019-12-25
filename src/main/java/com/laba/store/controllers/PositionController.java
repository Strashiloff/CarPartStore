package com.laba.store.controllers;

import com.laba.store.domain.Position;
import com.laba.store.domain.Supply;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("position")
public class PositionController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Position> getAllPosition() throws SQLException { return dataBaseService.getPositionFromSupply(); }

  @PostMapping
  public HashMap<String, String> addPosition(@RequestBody Position position) throws SQLException { return dataBaseService.addPositionRequest(position); }

  @PutMapping
  public HashMap<String, String> savePosition(@RequestBody Position position) throws SQLException { return dataBaseService.savePositionRequest(position); }

  @PostMapping("delete")
  public HashMap<String, String> deletePosition(@RequestBody Position position) throws SQLException { return dataBaseService.deletePositionRequest(position); }
}
