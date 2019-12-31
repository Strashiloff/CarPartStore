package com.laba.store.controllers;

import com.laba.store.domain.Country;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("country")
public class CountryController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Country> getAll() throws SQLException { return dataBaseService.getAllCountries(); }

  @PostMapping
  public HashMap<String, Boolean> addCountry(@RequestBody Country country) throws SQLException { return dataBaseService.addCountryRequest(country); }

  @PutMapping
  public HashMap<String, Boolean> saveCountry(@RequestBody Country country) throws SQLException { return dataBaseService.saveCountryRequest(country); }

  @PostMapping("delete")
  public HashMap<String, Boolean> deleteCountry(@RequestBody Country country) throws SQLException { return dataBaseService.deleteCountryRequest(country); }
}
