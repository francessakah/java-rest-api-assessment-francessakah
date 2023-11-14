package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.data.FileHandler;
import com.cbfacademy.apiassessment.data.PropertyDTO;
import com.cbfacademy.apiassessment.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * CRUD operations for properties
 * C - create new property
 * R - read all/read by ID
 * U - update property by ID
 * D - delete property by ID
 */
@Service
public class PropertyService {

    @Autowired
    private FileHandler fileHandler;

    public PropertyDTO create(Property property) throws IOException {
        return fileHandler.write(property);
    }

    public List<PropertyDTO> read() throws FileNotFoundException {
        return fileHandler.read();
    }

    public void delete(Integer id) throws IOException {
        fileHandler.delete(id);
    }

    public PropertyDTO readById(Integer id) throws FileNotFoundException {
        return fileHandler.readById(id);
    }
}
