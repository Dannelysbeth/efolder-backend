package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.AddressNotFoundException;
import com.example.efolder.model.Address;
import com.example.efolder.model.User;
import com.example.efolder.repository.AddressRepository;
import com.example.efolder.service.definition.AddressService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserService userService;

    @Override
    @PreAuthorize("hasAuthority('ADDRESS_WRITE_ANY')")
    public Address saveAnyAddress(Address address) {
        User user = userService.getUser(address.getUser().getUsername());
        user.setAddress(address);
        userService.updateUser(user);
        return addressRepository.save(address);
    }

    @Override
    @PreAuthorize("hasAuthority('ADDRESS_READ_ANY')")
    public Address getAnyAddress(String username) {
        User user = userService.getUser(username);
        return addressRepository.findById(user.getId())
                .orElseThrow(AddressNotFoundException::new);
    }

    @Override
    @PreAuthorize("hasAuthority('ADDRESS_READ_OWN')")
    public Address getOwnAddress() {
        User user = userService.getLoggedUser();
        return addressRepository.findById(user.getId())
                .orElseThrow(AddressNotFoundException::new);
    }

    @Override
    @PreAuthorize("hasAuthority('ADDRESS_WRITE_OWN')")
    public Address saveOwnAddress(Address address) {
        User user = userService.getLoggedUser();
        user.setAddress(address);
        userService.updateUser(user);
        return addressRepository.save(address);
    }
}
