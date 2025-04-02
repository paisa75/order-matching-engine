package com.tosan.ome.service;

import com.tosan.ome.controller.dtos.UserDto;
import com.tosan.ome.repository.entity.User;
import com.tosan.ome.repository.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserServicePort {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto saveUser(UserDto dto) {
        User user = new User();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, user);
        User savedUser = userRepository.save(user);
        dto.setPassword("******");
        dto.setUserId(savedUser.getUserId());
        return dto;
    }

    @Override
    public boolean findByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return true;
        }
        return false;
    }
}