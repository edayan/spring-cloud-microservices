package com.appdeveloperblog.photoapp.api.users.service;

import com.appdeveloperblog.photoapp.api.users.shared.UserDTO;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID().toString());
        return null;
    }
}
