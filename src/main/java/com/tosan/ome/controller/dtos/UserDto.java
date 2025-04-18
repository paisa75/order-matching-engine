package com.tosan.ome.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must contain only letters and numbers.")
    @NotBlank(message = "Username must not be blank")
    @Size(max = 20, message = "Username must not be longer than 20 characters")
    private String username;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
//    @Pattern(
//            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
//            message = "Password must contain at least one uppercase letter, one digit, and one special character"
//    )
    private String password;
}
