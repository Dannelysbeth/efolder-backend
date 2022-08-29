package com.example.efolder.model.dto.respones;


import com.example.efolder.model.Address;
import lombok.Builder;
import lombok.Data;

@Data
public class AddressResponse {
    private String firstname;
    private String lastname;
    private String country;
    private String county;
    private String city;
    private String street;
    private String buildingNumber;
    private String flatNumber;
    private String zipcode;

    @Builder
    public AddressResponse(Address address){
        this.firstname = address.getUser().getFirstname();
        this.lastname = address.getUser().getLastname();
        this.country = address.getCountry();
        this.county = address.getCounty();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.buildingNumber = address.getBuildingNumber();
        this.flatNumber = address.getFlatNumber();
        this.zipcode = address.getZipcode();
    }
}
