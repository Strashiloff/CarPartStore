package com.laba.store.controllers;

import com.laba.store.domain.Purveyor;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("purveyor")
public class PurveyorController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Purveyor> getAll() { return dataBaseService.getAllPurveyorRequest(); }

  @PostMapping
  public boolean addPurveyor(@RequestBody Purveyor purveyor) { return dataBaseService.addPurveyorRequest(purveyor); }

  @PutMapping
  public boolean savePurveyor(@RequestBody Purveyor purveyor) { return dataBaseService.savePurveyorRequest(purveyor); }

  @DeleteMapping
  public boolean deletePurveyor(@RequestBody Purveyor purveyor) { return dataBaseService.deletePurveyorRequest(purveyor); }
}
