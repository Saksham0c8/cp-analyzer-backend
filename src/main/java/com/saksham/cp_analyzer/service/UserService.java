package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.dto.UserDTO;
import com.saksham.cp_analyzer.entity.User;
import com.saksham.cp_analyzer.repository.SubmissionRepository;
import com.saksham.cp_analyzer.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service

public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getRating(),
                user.getTotalSolved()
        );
    }
}

