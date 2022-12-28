package com.example.efolder.model.dto.responses;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class EmployeeExtendedResponse {
    UserResponse user;
    EmploymentResponse employment;
    AddressResponse address;
    Collection<String> roles;


}
