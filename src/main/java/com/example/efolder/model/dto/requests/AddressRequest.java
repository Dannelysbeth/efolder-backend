package com.example.efolder.model.dto.requests;

import com.example.efolder.exceptions.EmptyFieldException;
import com.example.efolder.model.Address;
import com.example.efolder.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class AddressRequest {

    @NotBlank
    private String country;

    private String zipcode;

    private String county;

    @NotBlank
    private String city;

    private String street;

    private String buildingNumber;

    private String flatNumber;

    public boolean checkIfEmpty() {
        if (city == null)
            throw new EmptyFieldException("city");
        if (country == null)
            throw new EmptyFieldException("country");
        return true;
    }

    public Address buildAddress(User user) {
        checkIfEmpty();
        return Address.builder()
                .id(user.getId())
                .user(user)
                .country(country)
                .city(city)
                .buildingNumber(buildingNumber)
                .county(county)
                .flatNumber(flatNumber)
                .street(street)
                .zipcode(zipcode)
                .build();
    }


}
