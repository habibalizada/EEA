package com.estockmarket.userauth.service;

import com.estockmarket.userauth.data.UserEntity;
import com.estockmarket.userauth.data.UsersRepository;
import com.estockmarket.userauth.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersServiceImp implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        userEntity.setEncryptedPassword("test");

        usersRepository.save(userEntity);
        return null;
    }
}
