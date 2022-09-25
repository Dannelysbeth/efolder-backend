package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.EmailExistsException;
import com.example.efolder.exceptions.UserNotFoundException;
import com.example.efolder.model.Role;
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

    private boolean emailExistsInDatabase(UserInfo user){
        if(userInfoRepository.existsByEmail(user.getEmail()))
            return true;
        return false;
    }

    @Override
    public UserInfo getUser(String username) {
        return userInfoRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserInfo saveUser(UserInfo user) {
        return userInfoRepository.save(user);
    }

    @Override
    public UserInfo createEmployeeUser(UserInfo user) {
        if(emailExistsInDatabase(user)){
            throw new EmailExistsException();
        }
        return saveUser(addRegularEmployeeRole(user));

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

    @Override
    public UserInfo createSuperAdmin(UserInfo user) {
        if(emailExistsInDatabase(user)){
            throw new EmailExistsException();
        }
        return addSuperAdminRole(user);
    }

    @Override
    public UserInfo addSuperAdminRole(UserInfo user) {
        user.addRole(roleService.getRole("ROLE_SUPER_ADMIN"));
        return saveUser(user);
    }

    @Override
    public UserInfo addRegularEmployeeRole(UserInfo user) {
        user.addRole(roleService.getRole("ROLE_REGULAR_EMPLOYEE"));
        return saveUser(user);
    }

    @Override
    public UserInfo addManagerRole(UserInfo user) {
        user.addRole(roleService.getRole("ROLE_MANAGER"));
        return saveUser(user);
    }

    @Override
    public UserInfo addHRAdminRole(UserInfo user) {
        user.addRole(roleService.getRole("ROLE_HR_ADMIN"));
        return saveUser(user);
    }

}
