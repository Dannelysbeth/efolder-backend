package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.AddressNotFoundException;
import com.example.efolder.model.Address;
import com.example.efolder.model.User;
import com.example.efolder.model.UserInfo;
import com.example.efolder.model.dto.requests.CreateAddressRequest;
import com.example.efolder.repository.AddressRepository;
import com.example.efolder.service.definition.AddressService;
import com.example.efolder.service.definition.UserInfoService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserInfoService userService;

    @Override
    public Address saveAddress(Address address) {
        UserInfo userInfo = userService.getUser(address.getUser().getUsername());
        userInfo.setAddress(address);
        userService.updateUser(userInfo);
        return addressRepository.save(address);
    }

    @Override
    public Address getAddress(String username) {
        User user = userService.getUser(username);
        return addressRepository.findById(user.getId())
                .orElseThrow(AddressNotFoundException::new);
    }
}
