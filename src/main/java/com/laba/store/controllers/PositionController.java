package com.laba.store.controllers;

import com.laba.store.domain.Position;
import com.laba.store.domain.Supply;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("position")
public class PositionController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Position> getAllPosition(@RequestBody Supply supply){ return dataBaseService.getPositionFromSupply(supply.getId()); }

  @PostMapping
  public boolean addPosition(@RequestBody Position position) { return dataBaseService.addPositionRequest(position); }

  @PutMapping
  public boolean savePosition(@RequestBody Position position){ return dataBaseService.savePositionRequest(position); }

  @DeleteMapping
  public boolean deletePosition(@RequestBody Position position){ return dataBaseService.deletePositionRequest(position); }
}
