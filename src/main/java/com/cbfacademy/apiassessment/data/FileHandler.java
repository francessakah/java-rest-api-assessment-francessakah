package com.cbfacademy.apiassessment.data;

import com.cbfacademy.apiassessment.model.Property;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class FileHandler {
    public static String FILE_PATH = "src/main/resources/myproperties.json";

    /**
     * Read the json file for a list of propertyData
     * @return list of property data. If none, then it will return an empty list
     */
    public static List<PropertyData> read() {
        List<PropertyData> list = new ArrayList<>();

        try {
            Type type = new TypeToken<List<PropertyData>>() {}.getType();
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(FILE_PATH));
            list = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            // no file found return empty list
        }

        if(list == null){
            return new ArrayList<>();
        }

        return list;
    }

    /**
     * 1) Find the next id based on the last entry in the json file
     * 2) with this id, we create the propertyData which has the id
     * 3) if there is no current properties saved, then we create a new list to be saved
     * 4) given that no exceptions are thrown, then we can assume the propertyData has been saved
     * and we can return propertyData with it's appropriate id
     *
     * @param property
     * @return
     * @throws IOException
     */
    public static PropertyData write (Property property) throws IOException {
        Integer nextId = findNextId();

        PropertyData data = new PropertyData(property, findNextId());

        if (nextId == 1) {
            saveFile(List.of(data));
            return data;
        }

        List<PropertyData> list = read();
        list.add(data);

        saveFile(list);

        return data;
    }

    

    /**
     * Given lists are ordered based on insertion, the last element will always have the highest id
     * @return the next id or zero if the list is empty
     */
    public static Integer findNextId () {
        List<PropertyData> list = read();

        if (list.isEmpty())
            return 0;

        Integer listLastIndex = list.size() - 1;
        
        PropertyData lastProperty = list.get(listLastIndex);
        return lastProperty.getId() + 1;

    }

    /**
     * This method retrieves the new list with property data except the deleted property given by its id.
     * It then writes the new list into the json file
     * @param id
     * @throws IOException
     */
    public static void delete (Integer id) throws IOException {
        List<PropertyData> list = read();
        PropertyData data = readById(id);
        
        list.remove(data);
        saveFile(list);
    }

    /**
     * This method returns the property by its given id from the json file.
     * @param id
     * @return null if not found or the matched property data
     */
    public static PropertyData readById(Integer id) {
        List<PropertyData> list = read();
        for (PropertyData data : list) {
            if (data.getId() == id) return data;
        }
        
        return null;
    }

    /**
     * This method updates the json file by specific id with new property values given by the user.
     * 1) first gets the entire list in the json file to update later
     * 2) then copies the property data based on id given by the user 
     * 3) updates the property data at the correct position in the list
     * 4) saves the updated information in the json file
     * @param id
     * @param property
     * @return updated property data
     * @throws IOException
     */
    public static PropertyData update(Integer id, Property property) throws IOException {
        List<PropertyData> list = read();
        PropertyData currentProperty = readById(id);

        currentProperty.copy(property);

        Integer indexOf = list.indexOf(currentProperty);
        list.set(indexOf, currentProperty);

        saveFile(list);

        return currentProperty;
    }

    /**
     * This method overwrites the new list into the json file.
     * @param listToSave
     * @throws IOException
     */
    private static void saveFile(List<PropertyData> listToSave) throws IOException {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(listToSave, writer);
        }
    }
}

