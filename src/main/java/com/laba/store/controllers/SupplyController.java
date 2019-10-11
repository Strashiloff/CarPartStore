package com.laba.store.controllers;

import com.laba.store.domain.Supply;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("supply")
public class SupplyController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Supply> getAllSupply(){ return dataBaseService.getAllSupplyRequest(); }

  @PostMapping
  public boolean addSupply(@RequestBody Supply supply) { return dataBaseService.addSupplyRequest(supply); }

  @PutMapping
  public boolean saveSupply(@RequestBody Supply supply){ return dataBaseService.saveSupplyRequest(supply); }

  @DeleteMapping
  public boolean deleteSupply(@RequestBody Supply supply){ return dataBaseService.deleteSupplyRequest(supply); }
}
