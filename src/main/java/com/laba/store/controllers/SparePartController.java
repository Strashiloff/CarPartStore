package com.laba.store.controllers;

import com.laba.store.domain.Spare_part;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("part")
public class SparePartController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Spare_part> getAllSparePart() { return dataBaseService.getAllSparePartRequest(); }

  @PostMapping
  public boolean addSparePart(@RequestBody Spare_part spare_part) { return dataBaseService.addSparePartRequest(spare_part); }

  @PutMapping
  public boolean saveSparePart(@RequestBody Spare_part spare_part) { return dataBaseService.saveSparePartRequest(spare_part); }

  @DeleteMapping
  public boolean deleteSparePart(@RequestBody Spare_part spare_part) { return dataBaseService.saveSparePartRequest(spare_part); }
}
