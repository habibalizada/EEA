package com.estockmarket.userauth.service;

import com.estockmarket.userauth.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);
}
