package com.tosan.ome.service.validation;

import com.tosan.ome.controller.exceptions.UsernameAlreadyExistsException;
import com.tosan.ome.repository.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validateUsernameUniqueness(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("Username is already taken.");
        }
    }
}
