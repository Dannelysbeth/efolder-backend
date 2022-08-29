package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.EmailExistsException;
import com.example.efolder.exceptions.UserNotFoundException;
import com.example.efolder.model.UserInfo;
import com.example.efolder.repository.UserInfoRepository;
import com.example.efolder.repository.UserRepository;
import com.example.efolder.service.definition.RoleService;
import com.example.efolder.service.definition.UserInfoService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final RoleService roleService;
    private final UserService userService;

    @Override
    public UserInfo getUser(String username) {
        return userInfoRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserInfo saveUser(UserInfo user) {
        if(userInfoRepository.existsByEmail(user.getEmail()))
            throw new EmailExistsException();
        user.addRole(roleService.getRole("ROLE_REGULAR_EMPLOYEE"));
        user = (UserInfo) userService.saveUser(user);
        return userInfoRepository.save(user);
    }

    @Override
    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo getLoggedUser(){
        return getUser(userService.getLoggedUser().getUsername());
    }

    @Override
    public UserInfo updateUser(UserInfo user) {
        UserInfo userInfo;
        userInfo = user;
        return userInfoRepository.save(userInfo);
    }

}
