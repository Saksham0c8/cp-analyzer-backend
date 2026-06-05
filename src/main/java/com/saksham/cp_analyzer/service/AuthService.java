package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.dto.auth.*;
import com.saksham.cp_analyzer.entity.User;
import com.saksham.cp_analyzer.repository.UserRepository;
import com.saksham.cp_analyzer.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(
            RegisterRequestDTO request
    ) {

        if (
                userRepository
                        .findByUsername(
                                request.getUsername()
                        )
                        .isPresent()
        ) {
            throw new RuntimeException(
                    "Username already exists"
            );
        }

        User user = new User();

        user.setName(
                request.getName()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setUsername(
                request.getUsername()
        );

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        userRepository.save(user);

        return jwtService.generateToken(
                user.getUsername()
        );
    }

    public String login(
            LoginRequestDTO request
    ) {

        User user =
                userRepository
                        .findByUsername(
                                request.getUsername()
                        )
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "User not found"
                                        )
                        );

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!matches) {

            throw new RuntimeException(
                    "Invalid password"
            );
        }

        return jwtService.generateToken(
                user.getUsername()
        );
    }
}