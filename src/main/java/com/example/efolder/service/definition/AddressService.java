package com.example.efolder.service.definition;

import com.example.efolder.model.Address;
import com.example.efolder.model.dto.requests.AddressRequest;

public interface AddressService {
    Address saveAnyAddress(AddressRequest request, String username);

    Address saveAnyAddress(Address address);

    Address getAnyAddress(String username);

    Address getOwnAddress();

    Address saveOwnAddress(AddressRequest request);
}
