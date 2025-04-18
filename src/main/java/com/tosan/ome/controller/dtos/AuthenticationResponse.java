package com.tosan.ome.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private final String jwt;
}
