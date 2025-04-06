package com.tosan.ome.service;

import com.tosan.ome.controller.dtos.UserDto;

public interface UserServicePort {

    UserDto saveUser(UserDto dto);

    boolean findByUsername(String username);
}
