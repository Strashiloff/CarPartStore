package com.laba.store.controllers;

import com.laba.store.domain.Contract;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("contract")
public class ContractController {

  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Contract> getAllContract(){ return dataBaseService.getAllContractRequest(); }

  @PostMapping
  public boolean addContract(@RequestBody Contract contract) { return dataBaseService.addContractRequest(contract); }

  @PutMapping
  public boolean saveContract(@RequestBody Contract contract){ return dataBaseService.saveContractRequest(contract); }

  @DeleteMapping
  public boolean deleteContract(@RequestBody Contract contract){ return dataBaseService.deleteContractRequest(contract); }
}
