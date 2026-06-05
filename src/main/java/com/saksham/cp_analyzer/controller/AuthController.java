package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.dto.auth.*;
import com.saksham.cp_analyzer.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponseDTO register(

            @RequestBody
            RegisterRequestDTO request
    ) {

        String token =
                authService.register(request);

        return new AuthResponseDTO(
                token,
                request.getUsername()
        );
    }

    @PostMapping("/login")
    public AuthResponseDTO login(

            @RequestBody
            LoginRequestDTO request
    ) {

        String token =
                authService.login(request);

        return new AuthResponseDTO(
                token,
                request.getUsername()
        );
    }
}