package com.laba.store.controllers;

import com.laba.store.domain.Category;
import com.laba.store.domain.Country;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("country")
public class CountryController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Country> getAll() { return dataBaseService.getAllCountries(); }

  @PostMapping
  public boolean addCountry(@RequestBody Country country) { return dataBaseService.addCountryRequest(country); }

  @PutMapping
  public boolean saveCountry(@RequestBody Country country) { return dataBaseService.saveCountryRequest(country); }

  @DeleteMapping
  public boolean deleteCountry(@RequestBody Country country) { return dataBaseService.deleteCountryRequest(country); }
}
