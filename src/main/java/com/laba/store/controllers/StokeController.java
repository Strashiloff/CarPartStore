package com.laba.store.controllers;

import com.laba.store.domain.Stoke;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("stoke")
public class StokeController {

    @Autowired
    private DataBaseService dataBaseService;

    @GetMapping
    public ArrayList<Stoke> getAllStock() throws SQLException { return dataBaseService.getAllStock(); }

    @PostMapping
    public HashMap<String, Boolean> addStoke(@RequestBody Stoke stoke) throws SQLException { return dataBaseService.addStokeRequest(stoke); }

    @PutMapping
    public HashMap<String, Boolean> saveStoke(@RequestBody Stoke stoke) throws SQLException { return dataBaseService.saveStokeRequest(stoke); }

    @PostMapping("delete")
    public HashMap<String, Boolean> deleteStoke(@RequestBody Stoke stoke) throws SQLException { return dataBaseService.deleteStokeRequest(stoke); }
}
