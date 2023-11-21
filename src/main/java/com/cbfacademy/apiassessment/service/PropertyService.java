package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.data.FileHandler;
import com.cbfacademy.apiassessment.data.PropertyData;
import com.cbfacademy.apiassessment.model.Property;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

    /**
     * Add property to file with unique id
     * @param property to saved
     * @return property saved with id
     */
    public PropertyData create(Property property) {
        try {
            return FileHandler.write(property);
        } catch (IOException e) {
            throw new RuntimeException("Unable to create Property", e);
        }
    }

    /**
     * Retrieve all the properties on file in order of insertion
     * @return list of properties with id
     */
    public List<PropertyData> read(){
        return FileHandler.read();
    }

    /**
     * Retrieve a property by id given
     * @param id
     * @return property with its id
     */
    public PropertyData readById(Integer id){
        checkId(id);
        return FileHandler.readById(id);
    }

    /**
     * Update a property with a given id
     * @param property updated property
     * @param id property to update
     * @return updated property with its id
     */
    public PropertyData update(Integer id, Property property) {
        checkId(id);
        try {
            return FileHandler.update(id, property);
        } catch (IOException e) {
            throw new RuntimeException("Unable to update item", e);
        }
    }

    /**
     * Property to delete
     * @param id property to delete
     */
    public void delete(Integer id) {
        try {
            checkId(id);
            FileHandler.delete(id);
        } catch (IOException e){
            throw new RuntimeException("Unable to delete item", e);
        }
    }


    /**
     * As the array stored is in insertion order
     * A linear search is the most suitable algorithm to use
     * other search algorithms depend on the list to be sorted first -
     * the effort taken to sort then search outweighs a liner search
     * @param areacode - the first part of the postcode, i.e. SE1 8AA, this is SE8 part
     * @return the items in list that match postcode
     */
    private List<PropertyData> searchByLondonAreaCode(String areacode) {
        List<PropertyData> searchResults = new ArrayList<>();

        //get all the properties we have
        List<PropertyData> propertyDataList = FileHandler.read();

        for (PropertyData propertyData : propertyDataList) {
            String postcode = propertyData.getAddress().getPostcode();

            // Check if the postcode starts with the target area code
            if (postcode.toUpperCase().startsWith(areacode.toUpperCase() + " ")) {
                searchResults.add(propertyData);
            }
        }

        return searchResults;
    }
    public String getAverageSqrFootPrice(String areacode) {
        List<PropertyData> propertiesInAreacode = searchByLondonAreaCode(areacode);
        BigDecimal total = BigDecimal.ZERO;

        if(propertiesInAreacode.isEmpty()){
            return "No postcodes with area code " + areacode;
        }

        for (PropertyData property : propertiesInAreacode) {
            total = total.add(property.getPurchasePrice().divide(
                    BigDecimal.valueOf(property.getSizeInSqrFoot()),2, RoundingMode.HALF_UP
            ));
        }

        return String.format("%.2f", (total.divide(
                BigDecimal.valueOf(propertiesInAreacode.size()),2, RoundingMode.HALF_UP
        )));

    }


    private void checkId(Integer id) {
        if(FileHandler.readById(id) == null){
            throw new RuntimeException("No property found with id: " + id);
        }
    }


}
