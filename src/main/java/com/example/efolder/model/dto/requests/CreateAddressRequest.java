package com.example.efolder.model.dto.requests;

import com.example.efolder.model.Address;
import com.example.efolder.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class CreateAddressRequest {

    @NonNull
    private String country;

    private String zipcode;

    private String county;

    @NonNull
    private String city;

    private String street;

    private String buildingNumber;

    private String flatNumber;

    public Address addressRequest(User user) {
        return Address.builder()
                .user(user)
                .id(user.getId())
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
