package com.estockmarket.userauth.service;

import com.estockmarket.userauth.shared.UserDto;

public interface UsersService {
    UserDto createUser(UserDto userDetails);
}
