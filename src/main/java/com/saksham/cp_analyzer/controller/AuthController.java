package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.dto.auth.AuthResponseDTO;
import com.saksham.cp_analyzer.dto.auth.LoginRequestDTO;
import com.saksham.cp_analyzer.dto.auth.RegisterRequestDTO;
import com.saksham.cp_analyzer.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponseDTO register(
            @RequestBody RegisterRequestDTO request
    ) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(
            @RequestBody LoginRequestDTO request
    ) {

        return authService.login(request);
    }
}