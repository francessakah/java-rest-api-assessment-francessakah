package com.cbfacademy.apiassessment.model;

import java.math.BigDecimal;

public class Property {
    private Address address;
    private int noOfBedrooms;
    private double sizeInSqrFoot;
    private BigDecimal purchasePrice;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public void setNoOfBedrooms(int noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    public double getSizeInSqrFoot() {
        return sizeInSqrFoot;
    }

    public void setSizeInSqrFoot(double sizeInSqrFoot) {
        this.sizeInSqrFoot = sizeInSqrFoot;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
