package com.laba.store.controllers;

import com.laba.store.domain.Type;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("type")
public class TypeController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Type> getAll() { return dataBaseService.getAllTypes(); }

  @PostMapping
  public boolean addType(@RequestBody Type type) { return dataBaseService.addTypeRequest(type); }

  @PutMapping
  public boolean saveType(@RequestBody Type type) { return dataBaseService.saveTypeRequest(type); }

  @DeleteMapping
  public boolean deleteType(@RequestBody Type type) { return dataBaseService.deleteTypeRequest(type); }
}
