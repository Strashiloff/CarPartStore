package com.laba.store.controllers;

import com.laba.store.domain.Buy;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("buys")
public class BuyController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Buy> getAllBuy() throws SQLException { return dataBaseService.getAllBuyRequest(); }

  @GetMapping("defect")
  public ArrayList<Buy> getBuy(@RequestBody Buy buy) throws SQLException { return dataBaseService.getBuyFromDefect(buy.getId()); }

  @PostMapping
  public HashMap<String, Boolean> addBuy(@RequestBody Buy buy) throws SQLException { return dataBaseService.addBuyRequest(buy); }

  @PutMapping
  public HashMap<String, Boolean> saveBuy(@RequestBody Buy buy) throws SQLException { return dataBaseService.saveBuyRequest(buy); }

  @PostMapping("delete")
  public HashMap<String, Boolean> deleteBuy(@RequestBody Buy buy) throws SQLException { return dataBaseService.deleteBuyRequest(buy); }
}
