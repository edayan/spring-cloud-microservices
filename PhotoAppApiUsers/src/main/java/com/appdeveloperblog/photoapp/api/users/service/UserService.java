package com.appdeveloperblog.photoapp.api.users.service;

import com.appdeveloperblog.photoapp.api.users.shared.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserDetailsByEmail(String email);
}
