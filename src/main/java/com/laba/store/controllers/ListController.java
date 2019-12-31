package com.laba.store.controllers;

import com.laba.store.domain.List;
import com.laba.store.domain.Request;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("list")
public class ListController {
  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<List> getAllList() throws SQLException { return dataBaseService.getListFromRequest(); }

  @PostMapping
  public HashMap<String, Boolean> addList(@RequestBody List list) throws SQLException { return dataBaseService.addListRequest(list); }

  @PutMapping
  public HashMap<String, Boolean> saveList(@RequestBody List list) throws SQLException { return dataBaseService.saveListRequest(list); }

  @PostMapping("delete")
  public HashMap<String, Boolean> deleteList(@RequestBody List list) throws SQLException { return dataBaseService.deleteListRequest(list); }
}
