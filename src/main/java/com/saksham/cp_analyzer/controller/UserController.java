package com.saksham.cp_analyzer.controller;

import com.saksham.cp_analyzer.entity.User;
import com.saksham.cp_analyzer.repository.UserRepository;
import com.saksham.cp_analyzer.service.UserService;
import com.saksham.cp_analyzer.dto.UserDTO;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    public UserController(
            UserRepository userRepository,
            UserService userService
    ) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // ADD USER
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {

        return userRepository.save(user);
    }

    // GET USER
    @GetMapping("/{username}")
    public UserDTO getUser(
            @PathVariable String username
    ) {

        return userService.getUserByUsername(username);
    }
}