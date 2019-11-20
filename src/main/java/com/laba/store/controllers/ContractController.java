package com.laba.store.controllers;

import com.laba.store.domain.Contract;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("contract")
public class ContractController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Contract> getAllContract() throws SQLException { return dataBaseService.getAllContractRequest(); }

  @PostMapping
  public HashMap<String, String> addContract(@RequestBody Contract contract) throws SQLException { return dataBaseService.addContractRequest(contract); }

  @PutMapping
  public HashMap<String, String> saveContract(@RequestBody Contract contract) throws SQLException { return dataBaseService.saveContractRequest(contract); }

  @PostMapping("delete")
  public HashMap<String, String> deleteContract(@RequestBody Contract contract) throws SQLException { return dataBaseService.deleteContractRequest(contract); }
}
