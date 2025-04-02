package com.tosan.ome.controller.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String username;
    private String password;
}
