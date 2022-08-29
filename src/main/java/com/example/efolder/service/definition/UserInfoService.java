package com.example.efolder.service.definition;


import com.example.efolder.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    UserInfo getUser(String username);

    UserInfo saveUser(UserInfo user);

    List<UserInfo> getAllUsers();

    UserInfo getLoggedUser();

    UserInfo updateUser(UserInfo user);

}
