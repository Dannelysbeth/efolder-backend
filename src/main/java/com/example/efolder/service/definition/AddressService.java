package com.example.efolder.service.definition;

import com.example.efolder.model.Address;

public interface AddressService {
    Address saveAnyAddress(Address address);

    Address getAnyAddress(String username);

    Address getOwnAddress();

    Address saveOwnAddress(Address address);
}
