package com.example.efolder.model.dto.requests;

import com.example.efolder.exceptions.BusinessException;
import com.example.efolder.model.Address;
import com.example.efolder.model.Employment;
import com.example.efolder.model.User;
import com.example.efolder.service.definition.TeamService;
import com.example.efolder.service.definition.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CreateEmployeeRequest {

    CreateUserRequest user;
    CreateEmploymentRequest employment;
    CreateAddressRequest address;

    public boolean checkIfAllRequiredAreAvailable() {
        if (user.checkIfNotEmpty() && employment.checkIfNotNull() && address.checkIfEmpty())
            return true;
        throw new BusinessException(HttpStatus.NOT_ACCEPTABLE.value(), "There is something with the required fields");
    }


    public User returnBasicUser() {
        return this.user.userRequest();
    }

    public Employment returnEmployment(UserService userService, TeamService teamService, String username) {
        return employment.employmentRequest(userService, teamService, username);
    }

    public Address returnBasicAddress(User user) {
        return address.addressRequest(user);
    }

    public User employeeRequest(UserService userService, TeamService teamService) {
        User user = this.user.userRequest();
        Employment employment = this.employment.employmentRequest(userService, teamService, user.getUsername());
        Address address = this.address.addressRequest(user);
        user.setEmployment(employment);
        user.setAddress(address);
        return user;
    }
}
