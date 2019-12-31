package com.laba.store.controllers;

import com.laba.store.domain.Buy;
import com.laba.store.domain.Request;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("request")
public class RequestController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Request> getAllRequest() throws SQLException { return dataBaseService.getAllRequestRequest(); }

  @GetMapping("buy")
  public ArrayList<Request> getRequest(@RequestBody Buy buy) throws SQLException { return dataBaseService.getRequestFromBuy(buy.getId()); }

  @PostMapping
  public HashMap<String, Boolean> addRequest(@RequestBody Request request) throws SQLException { return dataBaseService.addRequestRequest(request); }

  @PutMapping
  public HashMap<String, Boolean> saveRequest(@RequestBody Request request) throws SQLException { return dataBaseService.saveRequestRequest(request); }

  @PostMapping("delete")
  public HashMap<String, Boolean> deleteRequest(@RequestBody Request request) throws SQLException { return dataBaseService.deleteRequestRequest(request); }
}
