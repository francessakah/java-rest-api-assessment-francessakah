# Property API - README

## Table of contents
1) Description
2) Get started
3) Folder Structure
4) API's
5) Technology used
6) Unit Test

## Description
This simple REST API app aims to facilitate access to a user's property portfolio electronically. 
It allows users to perform CRUD operations on individual properties within their portfolio through a single application. 
The information is saved into a JSON file.

## Get started
To get started run the App.java Spring Boot application

To run tests run mvn tests

### Prerequisites
You will require the following to get started
- Java version 17
- JDK 17 (or higher)
- Connection to internet to download maven dependencies

## Folder Structure
The folder structure follows the Spring Boot conventions
- `Controller`: Receives API requests
- `Data`: Contains all things data, FileHandler to read/write data to json file and PropertyData, the class type that is saved.
- `Model`: Contains the class types shared throughout the packages
- `Service`: Responsible for all logic and handling of data

The flow of data is `Users API request` > `Controller` > `Service` > `Data` > `Service` > `Controller` > `User API response`

## API's
API's available are:

| Method | Path                                    | Request  | Response           | Description                                             |
|--------|-----------------------------------------|----------|--------------------|---------------------------------------------------------|
| POST   | /properties                             | Property | PropertyData       | Create Property                                         |
| GET    | /properties                             |          | List<PropertyData> | Read all Properties                                     |
| GET    | /properties/{id}                        |          | PropertyData       | Read Property by Id                                     |
| PUT    | /properties/{id}                        | Property | PropertyData       | Update Property                                         |
| DELETE | /properties/{id}                        |          |                    | Delete Property by Id                                   |
| GET    | /properties/postcode/{postcode}/average |          | Double             | Average of price per sqr foot on first half of Postcode |

Property request example:
```json
{
    "address": {
        "houseNumber": "22",
        "houseName": "Melrose way",
        "streetName": "Bermondsey Street",
        "postcode": "SE1 4AV"
    },
    "noOfBedrooms": 2,
    "priceBySqrFoot": 10.40,
    "purchasePrice": 10000.00
}
```

PropertyData response Example
```json
{
    "id": 1234,
    "address": {
        "houseNumber": "22",
        "houseName": "Melrose way",
        "streetName": "Bermondsey Street",
        "postcode": "SE1 4AV"
    },
    "noOfBedrooms": 2,
    "priceBySqrFoot": 10.40,
    "purchasePrice": 10000.00
}
```

The Property.java class does not have the id property within it. 
This was to ensure that the integrity of the id was managed by the service.
This is set at creation and cannot be changed thereafter.
For this reason, only the property is passed in the request. 
The PropertyData is the response containing the id.

The id is responsible for most of the CRUD operations.

To ensure that the only difference between Property and PropertyData is the id,
PropertyData extends Property. 

## Technologies Used
Dependencies:
- GSON: to transform Java objects into JSON, and save data to file
- Spring Boot: to handle API requests

Since CRUD is the most of the API operations it was important to read and update/delete as the user
chooses. A list was chosen to handle the functionality as HashMaps cannot ensure order of insertion.

For ensuring the id was unique this was important.

As the data saved was in order of insertion, then the last id was always the biggest.

Also with this in mind, when searching, the best algorithm to use was Linear Search as other
algorithms meant that the list should be sorted. The computational efforts to sort then search outweighed the efforts
to Linear Search efforts.

## Unit Test
FileHandlerTest creates an entry in the test properties.json location before each test to ensure
the test is independent. After each test it is then deleted again to ensure independence and fair testing
