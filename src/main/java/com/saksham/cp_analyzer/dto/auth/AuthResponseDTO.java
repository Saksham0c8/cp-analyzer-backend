package com.saksham.cp_analyzer.dto.auth;

public class AuthResponseDTO {

    private String token;
    private Long userId;
    private String username;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String token, Long userId, String username) {
        this.token = token;
        this.userId = userId;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}