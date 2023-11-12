package com.example.demospringboot.controller;

import com.example.demospringboot.model.Property;
import com.example.demospringboot.data.PropertyDTO;
import com.example.demospringboot.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/properties")
public class PropertyController {
    @Autowired
    private PropertyService service;

    @PostMapping
    public PropertyDTO create(@RequestBody Property property){
        return service.create(property);
    }

    @GetMapping
    public HashMap<Integer, PropertyDTO> read(){
        return service.read();
    }
}
