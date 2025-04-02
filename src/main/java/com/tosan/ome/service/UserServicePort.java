package com.tosan.ome.service;

import com.tosan.ome.controller.dtos.UserDto;

public interface UserServicePort {

    public UserDto saveUser(UserDto dto);

    public boolean findByUsername(String username);
}
