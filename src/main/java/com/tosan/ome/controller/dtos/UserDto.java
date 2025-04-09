package com.tosan.ome.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must contain only letters and numbers.")
    @NotBlank(message = "Username must not be blank")
    @Size(max = 20, message = "Username must not be longer than 20 characters")
    private String username;

    @NotBlank(message = "Password must not be blank")
    private String password;

    // Constructor for test cases and easier initialization
    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
