package com.laba.store.controllers;

import com.laba.store.domain.Category;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("category")
public class CategoryController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Category> getAll() throws SQLException { return dataBaseService.getAllCategories(); }

  @PostMapping
  public HashMap<String, Boolean> addCategory(@RequestBody Category category) throws SQLException { return dataBaseService.addCategoryRequest(category); }

  @PutMapping
  public HashMap<String, Boolean> saveCategory(@RequestBody Category category) throws SQLException { return dataBaseService.saveCategoryRequest(category); }

  @PostMapping("delete")
  public HashMap<String, Boolean> deleteCategory(@RequestBody Category category) throws SQLException { return dataBaseService.deleteCategoryRequest(category); }
}
