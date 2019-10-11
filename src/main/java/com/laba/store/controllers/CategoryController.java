package com.laba.store.controllers;

import com.laba.store.domain.Category;
import com.laba.store.domain.Type;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("category")
public class CategoryController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Category> getAll() { return dataBaseService.getAllCategories(); }

  @PostMapping
  public boolean addCategory(@RequestBody Category category) { return dataBaseService.addCategoryRequest(category); }

  @PutMapping
  public boolean saveCategory(@RequestBody Category category) { return dataBaseService.saveCategoryRequest(category); }

  @DeleteMapping
  public boolean deleteCategory(@RequestBody Category category) { return dataBaseService.deleteCategoryRequest(category); }
}
