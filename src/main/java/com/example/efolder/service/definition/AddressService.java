package com.example.efolder.service.definition;

import com.example.efolder.model.Address;
import com.example.efolder.model.dto.requests.CreateAddressRequest;

public interface AddressService {
    Address saveAddress(Address address);

    Address getAddress(String username);
}
