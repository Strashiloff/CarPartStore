package com.laba.store.controllers;

import com.laba.store.domain.Section;
import com.laba.store.domain.Stoke;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("section")
public class SectionController {
    @Autowired
    private DataBaseService dataBaseService;

    @GetMapping
    public ArrayList<Section> getStokeSections(@RequestBody Stoke stoke) throws SQLException { return dataBaseService.getSectionsFromStock(stoke.getId()); }

    @GetMapping("all")
    public ArrayList<Section> getAllSection() throws SQLException { return dataBaseService.getAllSectionsRequest(); }

    @PostMapping
    public HashMap<String, String> saveSection(@RequestBody Section section) throws SQLException { return dataBaseService.addSectionRequest(section); }

    @PutMapping
    public HashMap<String, String> updateSection(@RequestBody Section section) throws SQLException { return dataBaseService.saveSectionRequest(section); }

    @PostMapping("delete")
    public HashMap<String, String> deleteSection(@RequestBody Section section) throws SQLException { return dataBaseService.deleteSectionRequest(section); }
}
