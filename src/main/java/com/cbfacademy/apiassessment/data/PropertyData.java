package com.cbfacademy.apiassessment.data;

import com.cbfacademy.apiassessment.model.Property;
import com.cbfacademy.apiassessment.service.PropertyService;

import java.math.BigDecimal;
import java.util.Objects;

public class PropertyData extends Property {
    private int id;
    //private BigDecimal estimatedPropertyValue;

    public PropertyData(Property property, Integer id){
        this.setId(id);
        this.setAddress(property.getAddress());
        this.setNoOfBedrooms(property.getNoOfBedrooms());
        this.setSizeInSqrFoot(property.getSizeInSqrFoot());
        this.setPurchasePrice(property.getPurchasePrice());
        //this.setEstimatedPropertyValue(estimatedPropertyValue);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertyData that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

//    public void calculateEstimatedPropertyValue(PropertyService propertyService) {
//        BigDecimal averageSqrFootPrice = new BigDecimal(propertyService.getAverageSqrFootPrice(getAddress().getPostcode()));
//        this.estimatedPropertyValue = averageSqrFootPrice.multiply(BigDecimal.valueOf(getSizeInSqrFoot()));
//    }
//
//    public BigDecimal getEstimatedPropertyValue() {
//        return estimatedPropertyValue;
//    }
//
//    private void setEstimatedPropertyValue(BigDecimal estimatedPropertyValue) {
//        this.estimatedPropertyValue = estimatedPropertyValue;
//    }

    public void copy(Property property){
        this.setAddress(property.getAddress());
        this.setNoOfBedrooms(property.getNoOfBedrooms());
        this.setSizeInSqrFoot(property.getSizeInSqrFoot());
        this.setPurchasePrice(property.getPurchasePrice());
    }
}
