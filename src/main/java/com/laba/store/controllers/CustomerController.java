package com.laba.store.controllers;

import com.laba.store.domain.Customer;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("customers")
public class CustomerController {
  @Autowired
  private DataBaseService dataBaseService;

  @GetMapping
  public ArrayList<Customer> getAllCustomer() throws SQLException { return dataBaseService.getAllCustomerRequest(); }

  @GetMapping("customer")
  public ArrayList<Customer> getCustomer(@RequestBody Customer buy) throws SQLException { return dataBaseService.getCustomerFromRequest(buy.getId()); }

  @PostMapping
  public HashMap<String, String> addCustomer(@RequestBody Customer buy) throws SQLException { return dataBaseService.addCustomerRequest(buy); }

  @PutMapping
  public HashMap<String, String> saveCustomer(@RequestBody Customer buy) throws SQLException { return dataBaseService.saveCustomerRequest(buy); }

  @PostMapping("delete")
  public HashMap<String, String> deleteCustomer(@RequestBody Customer buy) throws SQLException { return dataBaseService.deleteCustomerRequest(buy); }
}
