package com.tosan.ome.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TrackingCodeGenerator {
    private final Random random = new Random();

    public String generate(String type) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder(type.toUpperCase()).append("-");
        for (int i = 0; i < 6; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }
}
