package com.laba.store.controllers;

import com.laba.store.domain.Section;
import com.laba.store.domain.Stoke;
import com.laba.store.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("section")
public class SectionController {
    @Autowired
    private DataBaseService dataBaseService;

    @GetMapping
    public ArrayList<Section> getStokeSections(@RequestBody Stoke stoke){ return dataBaseService.getSectionsFromStock(stoke.getId()); }

    @GetMapping("all")
    public ArrayList<Section> getAllSection(@RequestBody Stoke stoke){ return dataBaseService.getAllSectionsRequest(); }

    @PostMapping
    public boolean saveSection(@RequestBody Section section){ return dataBaseService.addSectionRequest(section); }

    @PutMapping
    public boolean updateSection(@RequestBody Section section){ return dataBaseService.saveSectionRequest(section); }

    @DeleteMapping
    public boolean deleteSection(@RequestBody Section section){ return dataBaseService.deleteSectionRequest(section); }
}
