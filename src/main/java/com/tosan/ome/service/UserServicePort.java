package com.tosan.ome.service;

import com.tosan.ome.controller.dtos.UserDto;
import com.tosan.ome.repository.entity.User;

import java.util.Optional;

public interface UserServicePort {

    UserDto saveUser(UserDto dto);

    Optional<User> findByUsername(String username);

    UserDto registerUser(UserDto userDto);
}
