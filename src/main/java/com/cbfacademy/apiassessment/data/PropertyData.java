package com.cbfacademy.apiassessment.data;

import com.cbfacademy.apiassessment.model.Property;

import java.util.Objects;

public class PropertyData extends Property {
    private int id;

    public PropertyData(Property property, Integer id){
        this.setId(id);
        this.setAddress(property.getAddress());
        this.setNoOfBedrooms(property.getNoOfBedrooms());
        this.setPriceBySqrFoot(property.getPriceBySqrFoot());
        this.setPurchasePrice(property.getPurchasePrice());
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

    public void copy(Property property){
        this.setAddress(property.getAddress());
        this.setNoOfBedrooms(property.getNoOfBedrooms());
        this.setPriceBySqrFoot(property.getPriceBySqrFoot());
        this.setPurchasePrice(property.getPurchasePrice());
    }
}
