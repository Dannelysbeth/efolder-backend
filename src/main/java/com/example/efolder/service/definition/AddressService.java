package com.example.efolder.service.definition;

import com.example.efolder.model.Address;

public interface AddressService {
    Address saveAddress(Address address);

    Address getAddress(String username);
}
