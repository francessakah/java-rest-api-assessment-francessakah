package com.cbfacademy.apiassessment.data;

import com.cbfacademy.apiassessment.model.Property;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileHandler {
    private Gson gson = new Gson();
    private final String FILE_PATH = "C:\\Projects\\codingblackfemales\\java-rest-api-assessment-francessakah\\src\\main\\resources\\properties.json";

    /**
     * read the properties.json for a list of propertyDTO
     * if none, then it will return null
     * @return
     * @throws FileNotFoundException
     */
    public List<PropertyDTO> read() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(FILE_PATH));
        Type propertyMapType = new TypeToken<List<PropertyDTO>>() {}.getType();
        return gson.fromJson(reader, propertyMapType);
    }

    /**
     * 1) find the next ID based on the last entry in the properties.json file
     * 2) with this ID, we create the propertyDTO which has the ID
     * 3) if there is no current properties saved, then we create a new list to be saved
     * 4) given that no exceptions are thrown, then we can assume the propertyDTO has been saved
     * and we can return propertyDTO with it's appropriate ID
     *
     * @param property
     * @return
     * @throws IOException
     */
    public PropertyDTO write (Property property) throws IOException {
        PropertyDTO propertyDTO = new PropertyDTO(property);
        propertyDTO.setId(findNextId());
        List<PropertyDTO> list = read();

        if (list == null)
            list = new ArrayList<>();
        list.add(propertyDTO);

        try (Writer writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(list, writer);
        }
        return propertyDTO;
    }

    /**
     * this method is to return the next ID
     * given lists are ordered based on insertion, the last element will always have the highest ID
     * if the list is empty, then return zero
     *
     * @return
     * @throws FileNotFoundException
     */
    public Integer findNextId () throws FileNotFoundException {
        List<PropertyDTO> list = read();

        if (list == null)
            return 0;

        Integer listLastIndex = list.size() - 1;
        PropertyDTO lastProperty = list.get(listLastIndex);
        return lastProperty.getId() + 1;

    }

    /**
     * this method retrieves the list except the deleted property by its ID then writes the new list into properties.json
     * @param id
     * @throws IOException
     */
    public void delete (Integer id) throws IOException {
        List<PropertyDTO> list = read().stream().filter(propertyDTO -> propertyDTO.getId() != id).toList();

        try (Writer writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(list, writer);
        }
    }

    /**
     * this method returns the property by its ID from the properties.json
     *
     * @param id
     * @return
     * @throws FileNotFoundException
     */
    public PropertyDTO readById(Integer id) throws FileNotFoundException {
        List<PropertyDTO> list = read().stream().filter(propertyDTO -> propertyDTO.getId() == id).toList();
        return list.get(0);
    }
}

