package com.example.demospringboot.service;

import com.example.demospringboot.model.Property;
import com.example.demospringboot.data.PropertyDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PropertyService {
    private HashMap<Integer, PropertyDTO> propertyDB = new HashMap<Integer, PropertyDTO>();
    private Integer counter = 0;

    public PropertyDTO create(Property property){
        PropertyDTO dto = new PropertyDTO(property);
        dto.setId(counter);

        propertyDB.put(counter, dto);
        counter++;

        return dto;
    }

    public HashMap<Integer, PropertyDTO> read(){
        return propertyDB;
    }
}
