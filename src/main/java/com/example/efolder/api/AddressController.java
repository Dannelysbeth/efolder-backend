package com.example.efolder.api;

import com.example.efolder.model.Address;
import com.example.efolder.model.User;
import com.example.efolder.model.dto.requests.CreateAddressRequest;
import com.example.efolder.model.dto.responses.AddressResponse;
import com.example.efolder.service.definition.AddressService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;
    private final UserService userService;

    @PreAuthorize(("hasAnyRole('ROLE_REGULAR_EMPLOYEE', 'ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_MANAGER')"))
    @GetMapping()
    public ResponseEntity<AddressResponse> getMyAddress() {
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.ok().body(AddressResponse.builder()
                .address(addressService.getAddress(loggedUser.getUsername()))
                .build());
    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_MANAGER')"))
    @GetMapping("/{username}")
    public ResponseEntity<AddressResponse> getMyAddress(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(AddressResponse.builder()
                .address(addressService.getAddress(username))
                .build());
    }

    @PreAuthorize(("hasAnyRole('ROLE_REGULAR_EMPLOYEE', 'ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_MANAGER')"))
    @PostMapping()
    public ResponseEntity<AddressResponse> saveMyAddress(@RequestBody CreateAddressRequest addressRequest) {
        User loggedUser = userService.getLoggedUser();
//        User userInfo = userInfoService.getUser(loggedUser.getUsername());
        Address address = addressRequest.addressRequest(loggedUser);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/address/" + loggedUser.getUsername()).toUriString());
        return ResponseEntity.created(uri).body(AddressResponse.builder()
                .address(addressService.saveAddress(address))
                .build());
    }


    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_MANAGER')"))
    @PostMapping("/{username}")
    public ResponseEntity<AddressResponse> saveMyAddress(@PathVariable("username") String username, @RequestBody CreateAddressRequest addressRequest) {
        User user = userService.getUser(username);
        Address address = addressRequest.addressRequest(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/address/" + username).toUriString());
        return ResponseEntity.created(uri).body(AddressResponse.builder()
                .address(addressService.saveAddress(address))
                .build());
    }
}
