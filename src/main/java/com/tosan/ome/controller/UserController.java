package com.tosan.ome.controller;

import com.tosan.ome.controller.dtos.UserDto;
import com.tosan.ome.controller.exceptions.InvalidPayloadException;
import com.tosan.ome.controller.exceptions.UserIdAlreadyExistException;
import com.tosan.ome.service.UserServicePort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserController {

    private final UserServicePort userServicePort;

    @PostMapping("/register")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        if (Objects.isNull(userDto)) {
            throw new InvalidPayloadException("Payload cannot be Null");
        }
        if(userServicePort.findByUsername(userDto.getUsername())){
            throw new UserIdAlreadyExistException("Username is already taken");
        }
        return ResponseEntity.ok(userServicePort.saveUser(userDto));
    }
}
