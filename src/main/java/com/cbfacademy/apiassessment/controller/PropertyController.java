package com.cbfacademy.apiassessment.controller;

import com.cbfacademy.apiassessment.data.PropertyDTO;
import com.cbfacademy.apiassessment.model.Property;
import com.cbfacademy.apiassessment.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {
    @Autowired
    private PropertyService service;

    @PostMapping
    public PropertyDTO create(@RequestBody Property property) throws IOException {
        return service.create(property);
    }

    @GetMapping
    public List<PropertyDTO> read() throws FileNotFoundException {
        return service.read();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws IOException {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public PropertyDTO readById(@PathVariable Integer id) throws FileNotFoundException {
        return service.readById(id);
    }

}
