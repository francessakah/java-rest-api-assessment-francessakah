package com.cbfacademy.apiassessment.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.cbfacademy.apiassessment.model.Property;
import com.cbfacademy.apiassessment.model.Address;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class FileHandlerTest {
    private Property property = new Property();
    private final String TEST_FILE_PATH = "src/test/resources/properties.json";
    private final String WRONG_FILE_PATH = "src/not/real/path";

    @BeforeEach
    void setUp() throws IOException {
        Address address = new Address();
        address.setHouseNumber("12A");
        address.setHouseName("Barthold Estate");
        address.setStreetName("Newmold way");
        address.setPostcode("SW12 4RF");

        property.setAddress(address);
        property.setNoOfBedrooms(5);
        property.setPriceBySqrFoot(100.00);
        property.setPurchasePrice(new BigDecimal(340000.00));

        FileHandler.FILE_PATH = TEST_FILE_PATH;
        FileHandler.write(property);
    }

    @AfterEach
    void afterEach() throws IOException {
        List<PropertyData> list = FileHandler.read();
        for (PropertyData data: list) {
            FileHandler.delete(data.getId());
        }
    }

    @Test
    void writeSuccess() throws IOException {
        FileHandler.FILE_PATH = TEST_FILE_PATH;
        PropertyData data = FileHandler.write(property);

        assertProperty(data, 1);
    }

    @Test
    void writeFailure() {
        FileHandler.FILE_PATH = WRONG_FILE_PATH;
        Boolean threwException = false;

        try {
            FileHandler.write(property);
        } catch (IOException e){
            threwException = true;
        }

        assert (threwException);
    }

    @Test
    void readByIdSuccess() {
        FileHandler.FILE_PATH = TEST_FILE_PATH;
        PropertyData data = FileHandler.readById(1);

        assertProperty(data, 1);
    }

    @Test
    void readByIdFailure() {
        FileHandler.FILE_PATH = WRONG_FILE_PATH;
        PropertyData data = FileHandler.readById(1);

        assert(data == null);
    }

    @Test
    void updateSuccess() throws IOException {
        FileHandler.FILE_PATH = TEST_FILE_PATH;
        Property blankProperty = new Property();
        Address blankAddress = new Address();
        blankProperty.setAddress(blankAddress);

        PropertyData data = FileHandler.update(0, blankProperty);

        assert(data.getId() == 0);

        assert(0 == data.getNoOfBedrooms());
        assert(0.0 == data.getPriceBySqrFoot());
        assert(null == data.getPurchasePrice());

    }

    @Test
    void updateFailure()  {
        FileHandler.FILE_PATH = WRONG_FILE_PATH;
        Property blankProperty = new Property();
        Address blankAddress = new Address();
        blankProperty.setAddress(blankAddress);

        Boolean threwException = false;

        try {
            FileHandler.update(0, blankProperty);
        } catch (NullPointerException | IOException e) {
            threwException = true;
        }

        assert(threwException);
    }

    @Test
    void deleteFailure()  {
        FileHandler.FILE_PATH = WRONG_FILE_PATH;
        Boolean threwException = false;

        try {
            FileHandler.delete(0);
        } catch (IOException e) {
            threwException = true;
        }

        assert(threwException);
    }

    @Test
    void deleteSuccess() throws IOException {
        FileHandler.FILE_PATH = TEST_FILE_PATH;

        FileHandler.delete(0);
        PropertyData data = FileHandler.readById(0);

        assert(data == null);
    }


    private void assertProperty(PropertyData property1, Integer id){
        assert(property1.getId() == id);
        assert(property1.getAddress().getHouseNumber().equals(property.getAddress().getHouseNumber()));
        assert(property1.getAddress().getHouseName().equals(property.getAddress().getHouseName()));
        assert(property1.getAddress().getStreetName().equals(property.getAddress().getStreetName()));
        assert(property1.getAddress().getPostcode().equals(property1.getAddress().getPostcode()));

        assert(property.getNoOfBedrooms() == property1.getNoOfBedrooms());
        assert(property.getPriceBySqrFoot() == property1.getPriceBySqrFoot());
        assert(property.getPurchasePrice().equals(property1.getPurchasePrice()));
    }

}