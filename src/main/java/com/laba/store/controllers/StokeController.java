package com.laba.store.controllers;

import com.laba.store.domain.Stoke;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("stoke")
public class StokeController {

    @Autowired
    private DataBaseService dataBaseService;

    @GetMapping
    public ArrayList<Stoke> getAllStock(){ return dataBaseService.getAllStock(); }

    @PostMapping
    public boolean addStoke(@RequestBody Stoke stoke) { return dataBaseService.addStokeRequest(stoke); }

    @PutMapping
    public boolean saveStoke(@RequestBody Stoke stoke){ return dataBaseService.saveStokeRequest(stoke); }

    @DeleteMapping
    public boolean deleteStoke(@RequestBody Stoke stoke){ return dataBaseService.deleteStokeRequest(stoke); }
}
