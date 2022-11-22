package com.example.efolder.model.dto.requests;

import com.example.efolder.model.Address;
import com.example.efolder.model.Employment;
import com.example.efolder.model.User;
import com.example.efolder.model.enums.Gender;
import com.example.efolder.service.definition.AddressService;
import com.example.efolder.service.definition.TeamService;
import com.example.efolder.service.definition.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.Email;
import java.util.Date;

@Getter
@AllArgsConstructor
public class CreateEmployeeRequest {

    CreateUserRequest user;
    CreateEmploymentRequest employment;
    CreateAddressRequest address;

    public User returnBasicUser(){
        return this.user.userRequest();
    }

    public Employment returnEmployment(UserService userService, TeamService teamService){
        return employment.employmentRequest(userService, teamService);
    }

    public Address returnBasicAddress(User user){
        return address.addressRequest(user);
    }

    public User employeeRequest(UserService userService, TeamService teamService){
        User user = this.user.userRequest();
        Employment employment = this.employment.employmentRequest(userService, teamService);
        Address address = this.address.addressRequest(user);
        user.setEmployment(employment);
        user.setAddress(address);
        return user;
    }
}
